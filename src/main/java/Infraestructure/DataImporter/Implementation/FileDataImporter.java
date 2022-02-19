package Infraestructure.DataImporter.Implementation;

import Infraestructure.DataImporter.IDataImport;
import Infraestructure.Exception.InvalidFilePathException;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileDataImporter implements IDataImport {

    private File file;

    public FileDataImporter(){
        setFile("src/main/resources/GraphInput.txt");
    }

    public FileDataImporter(String path){
        setFile(path);
    }

    public File getFile() {
        return file;
    }

    public void setFile(String filePath) {
        if (filePath == null || filePath == ""){
            throw new InvalidFilePathException("Invalid file path");
        }
        try{
            File file = new File(filePath);
            if (!file.exists()) {
                throw new InvalidFilePathException("File can't be found");
            }
            this.file = file;
        }catch (Exception exception){
            throw new InvalidFilePathException("Some problems with file path", exception);
        }
    }

    public List<String> Read(){
        List<String> dataList = new ArrayList<>();
        String graphConcat = "";

        try{
            Scanner scanner = new Scanner(this.file.getAbsoluteFile());
            while(scanner.hasNextLine()){
                if (!graphConcat.isEmpty())
                    graphConcat += ", ";

                graphConcat += scanner.nextLine();
            }
            String[] nodes = graphConcat.split(",");
            Arrays.stream(nodes)
                    .map(node -> node.replaceAll(",", "").replaceAll(" ", "").trim())
                    .filter(node -> !node.equalsIgnoreCase(",") && !node.equalsIgnoreCase(" ") && !node.equalsIgnoreCase(""))
                    .forEach(node -> dataList.add(node));

        }catch (Exception exception){
            throw new InvalidFilePathException("Some problems with file path", exception);
        }

        return dataList;
    }
}
