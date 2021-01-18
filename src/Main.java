import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {

        GameProgress gamer1 = new GameProgress(20, 30, 40, 50);
        GameProgress gamer2 = new GameProgress(44, 55, 22, 33);
        GameProgress gamer3 = new GameProgress(2, 3, 4, 5);

        Main.saveGame("/Users/olakulikova/Games/savegames/save3.dat", gamer1, gamer2, gamer3);
        Main.zipFiles("/Users/olakulikova/Games/savegames/zip.zip", "/Users/olakulikova/Games/savegames/save3.dat");
        File file = new File("/Users/olakulikova/Games/savegames/save3.dat");
        file.delete();


    }

    public static void saveGame(String address, GameProgress... gamer) throws FileNotFoundException {
        try (
                FileOutputStream fos = new FileOutputStream(address, true);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {

            oos.writeObject(gamer);
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }

    public static void zipFiles(String archiveFilePath, String thePathToTheFile) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(archiveFilePath));
             FileInputStream fis = new FileInputStream(thePathToTheFile)) {
            ZipEntry entry = new ZipEntry("packed_save3.dat");
            zipOutputStream.putNextEntry(entry);
            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);
            zipOutputStream.write(bytes);
            zipOutputStream.closeEntry();
        } catch (Exception e) {
            System.out.println("Something went wrong " + e.getMessage());
        }
    }


}

