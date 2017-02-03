package HW2;

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.io.IOUtils;

/**
 * Created by Олексій on 01.02.2017.
 */
public class CollectionsTest {
    public static void main(String[] args) throws IOException{
        CollectionsTest ct = new CollectionsTest();
        //ct.DoTask6("file/Songs.txt");
        ct.DoTask6("file/task6.txt");
    }

    public void DoTask1(int workersCount){
        Task1 task1 = new Task1();
        task1.Fill(workersCount);
        String str = task1.GetTalentedWorker();
        System.out.println("В цьому місяці премію отримує: " + str);
    }
    public void DoTask2(){
        Task2 task2 = new Task2();
        List<Student> ar = task2.GetStudents(15);
        task2.StudentsCheck(ar,2);
    }
    public void DoTask3()
    {
        Map<Integer,String> workers = new HashMap<>();
        workers.put(1,"Morrison");
        workers.put(2,"Fox");
        workers.put(3,"Foster");
        workers.put(4,"Downey");
        workers.put(5,"Ford");
        workers.put(6,"Diaz");
        workers.put(7,"Amari");
        workers.put(8,"Ziegler");
        workers.put(9,"McCree");
        workers.put(10,"Smith");
        System.out.println("Введіть унікальний ключ працівника");
        System.out.println(workers.get(UserInput()));

    }
    public void DoTask4(String fileName, String word) throws IOException
    {
        InputStream is = getFileWithUtil(fileName);
        List<String> lines = IOUtils.readLines(is,"UTF-8");
        String result = "";
        for (String line : lines) {
            if (line.contains(word))
            {
                StringBuilder sb = new StringBuilder();
                String[] substrings = line.split(" ");
                if (word.equals(substrings[0]))//порівняння повноти слова. Наприклад, без цього "blac" буде розцінюватись як "black"
                {
                    sb.append("Слово \"" + substrings[0] + "\" означає " + substrings[1]);
                    result = sb.toString();
                    break;
                }
                else break;
            }
        }
        if (result.length()>0)
        {
            System.out.println(result);
        }
        else {
            System.out.println("На жаль, запитане слово не знайдено.");
        }
    }
    public void DoTask5(String fileName) throws IOException
    {
        InputStream is = getFileWithUtil(fileName);
        List<String> lines = IOUtils.readLines(is,"UTF-8");
        Collections.sort(lines,String.CASE_INSENSITIVE_ORDER);
        System.out.println("Відсортований список пісень:");
        for (String line : lines)
        {
            System.out.println(line);
        }
    }
    public void DoTask6(String fileName) throws IOException {
        //Завантажуємл файд ресурсів у потік
        InputStream is = getFileWithUtil(fileName);
        //Читаємо по рядках в List
        List<String> lines = IOUtils.readLines(is, "UTF-8");
        //ArrayList усіх пісень
        ArrayList<Song> songs = new ArrayList<>();
        //допоміжні змінні, аби не створювати їх в циклі
        String songName = "";
        String artist = "";
        int rating = 0;
        for(String line : lines)
        {
            //розбиваємо зчитані рядки по розділювачу "/"
            String[] sub = line.split("/");
            songName = sub[0];
            artist = sub[1];
            rating = Integer.parseInt(sub[2]);
            Song song = new Song(songName,artist,rating);
            songs.add(song);
        }
        System.out.println("Не сортоване:");
        for(Song song : songs) {
            System.out.println(song);
        }
        Collections.sort(songs);
        System.out.println("Сортовано за іменем:");
        for(Song song : songs) {
            System.out.println(song);
        }
        Collections.sort(songs,new Song());
        System.out.println("Сортовано за рейтингом:");
        for(Song song : songs) {
            System.out.println(song);
        }

    }
    public void DoTask7(){
        ArrayList<Node> Nodes = new ArrayList<>();
        Node n1 = new Node(10,null);
        Node n2 = new Node(9,n1);
        Node n3 = new Node(8,n2);
        Node n4 = new Node(7,n3);
        Node n5 = new Node(6,n4);
        Node n6 = new Node(5,n5);
        Node n7 = new Node(4,n6);
        Node n8 = new Node(3,n7);
        Node n9 = new Node(2,n8);
        Node n10 = new Node(1,n9);
        AddNode(n10,Nodes);
        System.out.println(Nodes);
    }
    public class Task1{
        ArrayList<String> EmployeesList;
        private final String [] namesPull = new String[]{"Anna","Elon","Garrett","John","Elza","Kate","Jully","Charles",
                "Victoria","Jack","Gaben","Sean","Viggo","Diana","Mia","Harrison",
                "Betty","Robert","Chris","Maria"};
        private final String [] surnamesPull = new String[]{"Shepard","Fox","Downey","Walker","Grehem","Grey","Anderson",
                "Erikson","Dewitt","Doe","Simpson","Smith","McConaghey",
                "Morrison","Oxton","Reigan","Foster","Ford","Hoffman","Jackson",
                "Rodrigez","Diaz"};
        public Task1(){
            EmployeesList = new ArrayList<>();
        }
        public void Fill(int workersCount){
            for (int i = 0; i < workersCount; i++) {
                int randomName = ThreadLocalRandom.current().nextInt(0,namesPull.length);
                int randomSurname = ThreadLocalRandom.current().nextInt(0,surnamesPull.length);
                String worker = new String(namesPull[randomName] + " " + surnamesPull[randomSurname]);
                EmployeesList.add(worker);
            }
        }
        public String GetTalentedWorker(){
            int index = ThreadLocalRandom.current().nextInt(0,EmployeesList.size()-1);
            return  EmployeesList.get(index);
        }
    }
    public class Task2{
        private final String [] namesPull = new String[]{"Anna","Elon","Garrett","John","Elza","Kate","Jully","Charles",
                "Victoria","Jack","Gaben","Sean","Viggo","Diana","Mia","Harrison",
                "Betty","Robert","Chris","Maria"};
        private final String [] surnamesPull = new String[]{"Shepard","Fox","Downey","Walker","Grehem","Grey","Anderson",
                "Erikson","Dewitt","Doe","Simpson","Smith","McConaghey",
                "Morrison","Oxton","Reigan","Foster","Ford","Hoffman","Jackson",
                "Rodrigez","Diaz"};
        public Task2(){}
        public List<Student> GetStudents(int studentCount) {
            List<Student> result = new ArrayList<>();
            int randomName;
            int randomSurname;
            int randomCourse;
            String studentName;
            for (int i = 0; i < studentCount; i++) {
                randomName = ThreadLocalRandom.current().nextInt(0, namesPull.length);
                randomSurname = ThreadLocalRandom.current().nextInt(0, surnamesPull.length);
                studentName = namesPull[randomName] + " " + surnamesPull[randomSurname];
                randomCourse = ThreadLocalRandom.current().nextInt(1,6);
                Student student = new Student(studentName,randomCourse);
                result.add(student);
            }
            return result;
        }
        public void StudentsCheck(List students, int course)
        {
            Iterator<Student> iterator = students.iterator();
            while(iterator.hasNext())
            {
                Student student = iterator.next();
                if (student.getCourse()==course)
                {
                    System.out.println(student);
                }
            }
        }
    }
    public class Student{
        //Потребую роз'яснення, адже все одно можу напряму звертатись до private полів і змінювати їх без getter'a
        private String Name;
        private int Course;
        public Student(){}
        public Student(String  name,int course)
        {
            this.Name = name;
            this.Course = course;
        }
        public String getName(){
            return this.Name;
        }
        public int getCourse(){
            return this.Course;
        }
        @Override
        public String toString() {
            return "Student{" +
                    "Name='" + Name + '\'' +
                    ", Course=" + Course +
                    '}';
        }
    }
    class Song implements Comparator<Song>, Comparable<Song>{
        private String SongName;
        private String Artist;
        private int Rating;
        public void setSongName(String name){this.SongName = name;}
        public void setArtist(String artist)
        {
            this.Artist = artist;
        }
        public void setRating(int rating)
        {
            this.Rating = rating;
        }
        public String getSongName()
        {
            return this.SongName;
        }
        public String getArtist()
        {
            return this.Artist;
        }
        public int getRating()
        {
            return this.Rating;
        }
        public Song(){}
        public Song(String songName, String artist, int rating)
        {
            setSongName(songName);
            setArtist(artist);
            setRating(rating);
        }
        public int compareTo(Song song){
            return (this.SongName).compareTo(song.SongName);
        }
        public int compare(Song song1, Song song2){
            return (-1)*(song1.Rating - song2.Rating);
        }

        @Override
        public String toString() {
            return "Пісня{" +
                    "Назва пісні='" + SongName + '\'' +
                    ", Виконавець='" + Artist + '\'' +
                    ", Рейтинг=" + Rating +
                    '}';
        }
    }
    public class Node{
        public Node next;
        public int elem;
        public Node(){}
        public Node(int Element, Node node)
        {
            this.elem = Element;
            this.next = node;
        }
    }
    //region Службові методи
     private InputStream getFileWithUtil(String fileName) throws IOException{

        InputStream result = null;
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            result = classLoader.getResourceAsStream(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    public static int UserInput() {
        Scanner input = new Scanner(System.in);
        boolean redo = true;
        int result = 0;
        while (redo == true) {
            System.out.println("Введіть число:");
            if (input.hasNextInt()) {
                result = input.nextInt();
                input.nextLine();
                redo = false;
            } else {
                System.out.println("Некоректне введення. Спробуйте ще раз.");
                input.nextLine();
            }
        }
        return result;
    }
    //endregion

    public static void AddNode(Node node, List Nodes){
        Nodes.add(0,node);
        if (node.next!=null)
        {
            AddNode(node.next,Nodes);
        }
    }
}
