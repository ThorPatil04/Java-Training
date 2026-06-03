import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileManager {

    // Create File
    public void createFile(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            Files.createFile(path);
            System.out.println("File created: " + filePath);
        } else {
            System.out.println("File already exists: " + filePath);
        }
    }

    // Copy File
    public void copyFile(String sourcePath, String destinationPath)
            throws IOException {

        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        Files.copy(source, destination,
                StandardCopyOption.REPLACE_EXISTING);

        System.out.println(
                "File copied from " + sourcePath +
                        " to " + destinationPath);
    }

    // Move File
    public void moveFile(String sourcePath, String destinationPath)
            throws IOException {

        Path source = Paths.get(sourcePath);
        Path destination = Paths.get(destinationPath);

        Files.move(source, destination,
                StandardCopyOption.REPLACE_EXISTING);

        System.out.println(
                "File moved from " + sourcePath +
                        " to " + destinationPath);
    }

    // Delete File
    public void deleteFile(String filePath) throws IOException {

        Path path = Paths.get(filePath);

        if (Files.deleteIfExists(path)) {
            System.out.println("File deleted: " + filePath);
        } else {
            System.out.println("File not found: " + filePath);
        }
    }

    // Search Files
    public List<String> searchFiles(String directory, String keyword)
            throws IOException {

        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(Paths.get(directory))) {

            return StreamSupport.stream(
                            stream.spliterator(), false)
                    .filter(path ->
                            path.getFileName()
                                    .toString()
                                    .contains(keyword))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        }
    }

    // Main Method
    public static void main(String[] args) {

        FileManager fm = new FileManager();

        try {

            // Create File
            fm.createFile("test.txt");

            // Copy File
            fm.copyFile(
                    "test.txt",
                    "copy_test.txt");

            // Move File
            fm.moveFile(
                    "copy_test.txt",
                    "moved_test.txt");

            // Search Files
            List<String> results =
                    fm.searchFiles(".", "test");

            System.out.println("\nSearch Results:");

            results.forEach(System.out::println);

            // Delete Files
            fm.deleteFile("test.txt");
            fm.deleteFile("moved_test.txt");

        } catch (IOException e) {

            System.out.println(
                    "Error: " + e.getMessage());
        }
    }
}