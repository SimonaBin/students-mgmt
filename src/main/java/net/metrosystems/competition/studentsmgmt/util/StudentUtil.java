package net.metrosystems.competition.studentsmgmt.util;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.metrosystems.competition.studentsmgmt.dto.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class StudentUtil {

    public List<Student> readData(String fileName) throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        MappingIterator<Student> personIter = new CsvMapper().readerWithTypedSchemaFor(Student.class)
                .readValues(file);

        return personIter.readAll();
    }

    public void writeDataToCSV(String fileName, List<Student> students) throws Exception {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.builder().addColumn("id").addColumn("firstName").addColumn("lastName").addColumn("dateOfBirth").addColumn("spec").addColumn("avg").build();
        ObjectWriter writer = mapper.writerFor(Student.class).with(schema);
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        writer.writeValues(file).writeAll(students).close();
    }

    public boolean deleteContentFromCSVFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
            writer.print("");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
        return  false;
    }
}
