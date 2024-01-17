package adventofcode.scraper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.checkerframework.checker.units.qual.s;

import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class InputScraper {

    
    private static final String filenameTemplate = "%s-day%s.txt";
    private static final String FolderPath = "./resources/";

    private static Stream<String> requestInputFile(String year, String day) throws IOException {
        String session = Dotenv
            .configure()
            .ignoreIfMissing()
            .load()
            .get("AOC_SESSION");

        OkHttpClient client = new OkHttpClient.Builder()
            .build();

        Request request = new Request.Builder()
            .url(String.format("https://adventofcode.com/%s/day/%s/input", year, day))
            .addHeader("Cookie", session)
            .build();

        Response response = client.newCall(request).execute();

        return Arrays.stream(Objects.requireNonNull(response.body()).string().split("\n"));
    }

    public static List<String> getInput(String year, String day) throws IOException {
        String filename = String.format("%s/" + filenameTemplate, FolderPath,year, day);
        File f = new File(filename);
        if ( ! f.isFile())
        {
            saveToFile(year, day, f);
        }

        System.out.println("loading " + filename + "from file");
        return Files.readAllLines(f.toPath(), Charset.defaultCharset() );
    }

    private static void saveToFile(String year, String day, File file) throws IOException {
        Path parent = Path.of(file.getParent());
        if ( ! Files.exists(parent)) {
            Files.createDirectories(parent);
        }

        Stream<String> inputFile = requestInputFile(year, day);
        Files.write(file.toPath(), (Iterable<String>) inputFile::iterator, StandardCharsets.UTF_8);
        System.out.println("saving file to " + file.getAbsolutePath());
    }   

}
