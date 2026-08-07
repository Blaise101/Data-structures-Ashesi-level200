import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static class TestReport {
        private int passed;
        private int failed;
        private int skipped;
        private final List<String> failures = new ArrayList<>();
        private final List<String> skips = new ArrayList<>();

        void pass(String message) {
            passed++;
            System.out.println("[PASS] " + message);
        }

        void fail(String message) {
            failed++;
            failures.add(message);
            System.out.println("[FAIL] " + message);
        }

        void skip(String message) {
            skipped++;
            skips.add(message);
            System.out.println("[SKIP] " + message);
        }
    }

    private static final class FixedHashKey {
        private final String raw;
        private final int forcedHash;

        private FixedHashKey(String raw, int forcedHash) {
            this.raw = raw;
            this.forcedHash = forcedHash;
        }

        @Override
        public int hashCode() {
            return forcedHash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FixedHashKey)) {
                return false;
            }
            FixedHashKey other = (FixedHashKey) obj;
            return this.raw.equals(other.raw);
        }
    }

    public static void main(String[] args) {
        TestReport report = new TestReport();
        System.out.println("=== CS222 Assignment 4 Verification Harness ===");
        System.out.println("This harness validates structure, behavior, and metrics.");
        System.out.println();

        runStructureChecks(report);
        runBehaviorChecks(report);
        printSummary(report);
    }

    private static void runStructureChecks(TestReport report) {
        System.out.println("--- Structure Checks ---");

        requireConstructor(report, StudentID.class, new Class<?>[]{ String.class }, "StudentID(String) constructor");
        requireMethod(report, StudentID.class, "getID", String.class, new Class<?>[]{}, "StudentID.getID(): String");
        requireMethod(report, StudentID.class, "toString", String.class, new Class<?>[]{}, "StudentID.toString(): String");
        requireMethod(report, StudentID.class, "equals", boolean.class, new Class<?>[]{ Object.class }, "StudentID.equals(Object): boolean");
        requireMethod(report, StudentID.class, "hashCode", int.class, new Class<?>[]{}, "StudentID.hashCode(): int");

        requireConstructor(report, Student.class, new Class<?>[]{ String.class, String.class, String.class }, "Student(String name, String email, String id) constructor");
        requireMethod(report, Student.class, "getID", StudentID.class, new Class<?>[]{}, "Student.getID(): StudentID");
        requireMethod(report, Student.class, "getName", String.class, new Class<?>[]{}, "Student.getName(): String");
        requireMethod(report, Student.class, "getEmail", String.class, new Class<?>[]{}, "Student.getEmail(): String");
        requireMethod(report, Student.class, "toString", String.class, new Class<?>[]{}, "Student.toString(): String");

        requireNodeShape(report);

        requireConstructor(report, HashTable.class, new Class<?>[]{}, "HashTable() constructor");
        requireMethod(report, HashTable.class, "put", void.class, new Class<?>[]{ Object.class, Object.class }, "HashTable.put(K, V): void");
        requireMethod(report, HashTable.class, "get", Object.class, new Class<?>[]{ Object.class }, "HashTable.get(K): V");
        requireMethod(report, HashTable.class, "remove", boolean.class, new Class<?>[]{ Object.class }, "HashTable.remove(K): boolean");
        requireMethod(report, HashTable.class, "size", int.class, new Class<?>[]{}, "HashTable.size(): int");
        requireMethod(report, HashTable.class, "display", void.class, new Class<?>[]{}, "HashTable.display(): void");
        requireMethod(report, HashTable.class, "getCollisionCount", int.class, new Class<?>[]{}, "HashTable.getCollisionCount(): int");
        requireMethod(report, HashTable.class, "getRehashCount", int.class, new Class<?>[]{}, "HashTable.getRehashCount(): int");

        Method shrinkMethod = findMethod(HashTable.class, "getShrinkCount", new Class<?>[]{});
        if (shrinkMethod == null || shrinkMethod.getReturnType() != int.class) {
            report.skip("Bonus method getShrinkCount(): int not found (extra credit is optional)");
        } else {
            report.pass("Bonus method getShrinkCount(): int found");
        }
        System.out.println();
    }

    private static void runBehaviorChecks(TestReport report) {
        System.out.println("--- Behavior Checks ---");
        testStudentIDBehavior(report);
        testStudentBehavior(report);
        testHashTableCoreBehavior(report);
        testHashTableMetricsBehavior(report);
        System.out.println();
    }

    private static void testStudentIDBehavior(TestReport report) {
        try {
            StudentID a = new StudentID("00012028");
            StudentID b = new StudentID("00012028");
            StudentID c = new StudentID("G90123");

            assertCondition(report, "00012028".equals(a.toString()), "StudentID preserves leading zeros in toString");
            assertCondition(report, a.equals(b), "StudentID equals works for identical IDs");
            assertCondition(report, !a.equals(c), "StudentID equals distinguishes different IDs");
            assertCondition(report, a.hashCode() == b.hashCode(), "StudentID hashCode is consistent for equal IDs");
        } catch (Throwable t) {
            report.fail("StudentID behavior test crashed: " + t.getMessage());
        }
    }

    private static void testStudentBehavior(TestReport report) {
        try {
            Student student = createStudentReflectively(report, "Ama Mensah", "ama@ashesi.edu.gh", "00012028");
            if (student == null) {
                report.fail("Student instance could not be created, so composition behavior was not verifiable");
                return;
            }
            assertCondition(report, student.getID() != null, "Student has-a StudentID object");
            assertCondition(report, "00012028".equals(student.getID().toString()), "Student stores ID correctly");
            assertCondition(report, "Ama Mensah".equals(student.getName()), "Student.getName returns expected value");
            assertCondition(report, "ama@ashesi.edu.gh".equals(student.getEmail()), "Student.getEmail returns expected value");
        } catch (Throwable t) {
            report.fail("Student behavior test crashed: " + t.getMessage());
        }
    }

    private static void testHashTableCoreBehavior(TestReport report) {
        try {
            HashTable<StudentID, Student> table = new HashTable<>();
            assertEquals(report, 0, table.size(), "HashTable starts empty");
            assertEquals(report, 0, table.getCollisionCount(), "Initial collision count is zero");
            assertEquals(report, 0, table.getRehashCount(), "Initial rehash count is zero");

            table.put(new StudentID("00012028"), createStudentReflectively(report, "Ama", "ama@ashesi.edu.gh", "00012028"));
            table.put(new StudentID("G90123"), createStudentReflectively(report, "John", "john@ashesi.edu.gh", "G90123"));
            table.put(new StudentID("M77810"), createStudentReflectively(report, "Kofi", "kofi@ashesi.edu.gh", "M77810"));
            assertEquals(report, 3, table.size(), "Size increments after new PUT operations");

            Student first = table.get(new StudentID("00012028"));
            assertCondition(report, first != null, "GET returns inserted student");

            table.put(new StudentID("00012028"), createStudentReflectively(report, "Ama Updated", "ama.updated@ashesi.edu.gh", "00012028"));
            assertEquals(report, 3, table.size(), "Updating an existing key does not increase size");

            Student updated = table.get(new StudentID("00012028"));
            assertCondition(report, updated != null && "Ama Updated".equals(updated.getName()), "PUT updates existing key value");

            Student missing = table.get(new StudentID("X00000"));
            assertCondition(report, missing == null, "GET returns null for missing key");

            boolean removed = table.remove(new StudentID("G90123"));
            assertCondition(report, removed, "REMOVE returns true when key exists");
            assertEquals(report, 2, table.size(), "Size decrements after successful REMOVE");

            boolean removedMissing = table.remove(new StudentID("G90123"));
            assertCondition(report, !removedMissing, "REMOVE returns false when key is absent");

            String displayOutput = captureDisplayOutput(table);
            assertCondition(report, displayOutput.trim().length() > 0, "DISPLAY prints stored records");
        } catch (Throwable t) {
            report.fail("HashTable core behavior test crashed: " + t.getMessage());
        }
    }

    private static void testHashTableMetricsBehavior(TestReport report) {
        try {
            HashTable<FixedHashKey, String> table = new HashTable<>();
            table.put(new FixedHashKey("k1", 1), "v1");
            assertEquals(report, 0, table.getCollisionCount(), "First insert into an empty bucket does not count as collision");

            table.put(new FixedHashKey("k2", 1), "v2");
            table.put(new FixedHashKey("k3", 1), "v3");
            assertEquals(report, 2, table.getCollisionCount(), "Inserting new keys into non-empty bucket increments collision count");

            table.put(new FixedHashKey("k2", 1), "v2-updated");
            assertEquals(report, 2, table.getCollisionCount(), "Updating existing key does not increment collision count");

            table.put(new FixedHashKey("k4", 4), "v4");
            table.put(new FixedHashKey("k5", 5), "v5");
            table.put(new FixedHashKey("k6", 6), "v6");
            table.put(new FixedHashKey("k7", 7), "v7");

            assertCondition(report, table.getRehashCount() >= 1, "Rehash count increments when load factor exceeds 0.75");
            assertEquals(report, 7, table.size(), "All inserts are retained after rehash");
            assertEquals(report, "v7", table.get(new FixedHashKey("k7", 7)), "Values remain retrievable after rehash");

            Method shrinkMethod = findMethod(table.getClass(), "getShrinkCount", new Class<?>[]{});
            if (shrinkMethod == null || shrinkMethod.getReturnType() != int.class) {
                report.skip("Bonus shrink behavior test skipped because getShrinkCount is absent");
                return;
            }

            table.remove(new FixedHashKey("k4", 4));
            table.remove(new FixedHashKey("k5", 5));
            table.remove(new FixedHashKey("k6", 6));
            table.remove(new FixedHashKey("k7", 7));

            int shrinkCount = (int) shrinkMethod.invoke(table);
            assertCondition(report, shrinkCount >= 1, "Shrink count increments when load factor drops below 0.25 at capacity > 8");
        } catch (Throwable t) {
            report.fail("HashTable metrics behavior test crashed: " + t.getMessage());
        }
    }

    private static Student createStudentReflectively(TestReport report, String name, String email, String id) {
        try {
            Constructor<Student> required = Student.class.getDeclaredConstructor(String.class, String.class, String.class);
            required.setAccessible(true);
            return required.newInstance(name, email, id);
        } catch (NoSuchMethodException ex) {
            report.fail("Required Student constructor missing: Student(String name, String email, String id)");
            // Fallback supports continued testing when students accidentally used the old parameter order.
            try {
                Constructor<Student> fallback = Student.class.getDeclaredConstructor(String.class, String.class, String.class);
                fallback.setAccessible(true);
                return fallback.newInstance(id, name, email);
            } catch (Throwable ignored) {
                return null;
            }
        } catch (Throwable t) {
            report.fail("Student constructor invocation failed: " + t.getMessage());
            return null;
        }
    }

    private static void requireNodeShape(TestReport report) {
        try {
            Class<?>[] nested = HashTable.class.getDeclaredClasses();
            Class<?> nodeClass = null;

            for (Class<?> clazz : nested) {
                if (clazz.getSimpleName().equals("Node")) {
                    nodeClass = clazz;
                    break;
                }
            }

            if (nodeClass == null) {
                report.fail("HashTable nested Node class not found");
                return;
            }

            Field keyField = nodeClass.getDeclaredField("key");
            Field valueField = nodeClass.getDeclaredField("value");
            Field nextField = nodeClass.getDeclaredField("next");

            if (keyField != null && valueField != null && nextField != null) {
                report.pass("HashTable nested Node structure contains key, value, and next fields");
            }
        } catch (NoSuchFieldException e) {
            report.fail("HashTable Node fields must include key, value, and next");
        } catch (Throwable t) {
            report.fail("Node structure verification failed: " + t.getMessage());
        }
    }

    private static void requireConstructor(TestReport report, Class<?> owner, Class<?>[] params, String label) {
        try {
            owner.getDeclaredConstructor(params);
            report.pass(label);
        } catch (NoSuchMethodException e) {
            report.fail(label + " missing");
        } catch (Throwable t) {
            report.fail(label + " check failed: " + t.getMessage());
        }
    }

    private static void requireMethod(TestReport report, Class<?> owner, String name, Class<?> returnType, Class<?>[] params, String label) {
        Method method = findMethod(owner, name, params);
        if (method == null) {
            report.fail(label + " missing");
            return;
        }
        if (method.getReturnType() != returnType) {
            report.fail(label + " has wrong return type: expected " + returnType.getSimpleName() + " but found " + method.getReturnType().getSimpleName());
            return;
        }
        report.pass(label);
    }

    private static Method findMethod(Class<?> owner, String name, Class<?>[] params) {
        try {
            return owner.getMethod(name, params);
        } catch (NoSuchMethodException e) {
            try {
                return owner.getDeclaredMethod(name, params);
            } catch (NoSuchMethodException ignored) {
                return null;
            }
        }
    }

    private static String captureDisplayOutput(HashTable<?, ?> table) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream replacement = new PrintStream(buffer);

        try {
            System.setOut(replacement);
            table.display();
        } finally {
            replacement.flush();
            System.setOut(original);
        }

        return buffer.toString();
    }

    private static void assertCondition(TestReport report, boolean condition, String successMessage) {
        if (condition) {
            report.pass(successMessage);
        } else {
            report.fail(successMessage);
        }
    }

    private static void assertEquals(TestReport report, Object expected, Object actual, String context) {
        boolean matches = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        if (matches) {
            report.pass(context);
        } else {
            report.fail(context + " (expected: " + expected + ", actual: " + actual + ")");
        }
    }

    private static void printSummary(TestReport report) {
        int total = report.passed + report.failed;
        System.out.println("=== Verification Summary ===");
        System.out.println("Checks passed: " + report.passed);
        System.out.println("Checks failed: " + report.failed);
        System.out.println("Checks skipped: " + report.skipped);
        System.out.println("Total scored checks: " + total);

        if (report.failed == 0) {
            System.out.println("RESULT: ALL REQUIRED CHECKS PASSED");
        } else {
            System.out.println("RESULT: SOME CHECKS FAILED");
            System.out.println("--- Failure Details ---");
            for (String failure : report.failures) {
                System.out.println("- " + failure);
            }
        }

        if (!report.skips.isEmpty()) {
            System.out.println("--- Skipped / Optional ---");
            for (String skip : report.skips) {
                System.out.println("- " + skip);
            }
        }
    }
}