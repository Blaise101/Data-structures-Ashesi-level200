package lab1;
public class MovieListApp {
    public static void main(String[] args) {
        MyArrayList<Movie> movieLines = new MyArrayList<>();

        System.out.println("--- 1. Adding Movies to Custom MyArrayList ---");
        movieLines.add(new Movie("Inception", 2010, 8.8));
        movieLines.add(new Movie("The Dark Knight", 2008, 9.0));
        movieLines.add(new Movie("Interstellar", 2014, 8.6));
        movieLines.add(new Movie("The Matrix", 1999, 8.7));
        
        // Display initial full list populated
        movieLines.display();
        System.out.println();

        System.out.println("--- 2. Calculating Average Movie Rating ---");
        double totalRatingSum = 0;
        // Basic computation iteration tracking running rating sum totals
        for (int i = 0; i < movieLines.size(); i++) {
            totalRatingSum += movieLines.get(i).getRating();
        }
        double averageRating = totalRatingSum / movieLines.size();
        System.out.printf("Average Rating of List: %.2f / 10\n\n", averageRating);

        System.out.println("--- 3. Removing a Movie by Title ('Interstellar') ---");
        String targetTitle = "Interstellar";
        boolean foundAndRemoved = false;

        // Dynamic loop search logic to find match and trigger custom structural remove
        for (int i = 0; i < movieLines.size(); i++) {
            if (movieLines.get(i).getTitle().equalsIgnoreCase(targetTitle)) {
                Movie removed = movieLines.remove(i);
                System.out.println("Successfully removed: " + removed);
                foundAndRemoved = true;
                break; // Break loop immediately after deletion to avoid index alignment issues
            }
        }
        if (!foundAndRemoved) {
            System.out.println("Movie entitled '" + targetTitle + "' not found.");
        }
        System.out.println();

        System.out.println("--- 4. Final Updated Movie List ---");
        movieLines.display();
    }
}