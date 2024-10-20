import java.io.*;
import java.util.*;

public class FileSplitter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Masukkan nama file yang akan dibaca: ");
        String filename = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per bagian: ");
        int linesPerPart = scanner.nextInt();
        scanner.nextLine();

        Queue<List<String>> partsQueue = new LinkedList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<String> currentPart = new ArrayList<>();
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                currentPart.add(line);
                lineCount++;

                if (lineCount == linesPerPart) {
                    partsQueue.add(new ArrayList<>(currentPart));
                    currentPart.clear();
                    lineCount = 0;
                }
            }

            if (!currentPart.isEmpty()) {
                partsQueue.add(currentPart);
            }

            System.out.println("\nFile berhasil dipotong. Bagian-bagian yang dihasilkan:");
            int partNumber = 1;
            while (!partsQueue.isEmpty()) {
                System.out.println("Bagian " + partNumber + ":");
                List<String> part = partsQueue.poll();
                for (String partLine : part) {
                    System.out.println(partLine);
                }
                System.out.println();
                partNumber++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File tidak ditemukan: " + filename);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }

        scanner.close();
    }
}
