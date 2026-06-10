package lab1;

// Blueprint class representing a Movie item object.
public class Movie {
    private String title;
    private int releaseYear;
    private double rating;

    // Constructor to initialize movie properties
    public Movie(String title, int releaseYear, double rating) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    // Getters for application usage
    public String getTitle() { return title; }
    public int getReleaseYear() { return releaseYear; }
    public double getRating() { return rating; }

    // Override toString for simple layout print during MyArrayList.display()
    @Override
    public String toString() {
        return "'" + title + "' (" + releaseYear + ") - Rating: " + rating + "/10";
    }
}
