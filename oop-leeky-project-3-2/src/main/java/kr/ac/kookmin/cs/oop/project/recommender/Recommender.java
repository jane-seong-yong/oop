package kr.ac.kookmin.cs.oop.project.recommender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kr.ac.kookmin.cs.oop.project.model.Song;

public abstract class Recommender {

    protected final Song[] songs;

    public abstract Song[] recommend(String studentId);

    public Recommender(String filePath) {
        this.songs = buildSongObjects(filePath);
    }
    
    /*
     * Read the input file and fill the Song[] array
     */
//    buildSongObjects 함수 구현. Song 클래스에서 생성자를 만들어 줘서 쉽게됨.
    private Song[] buildSongObjects(String filePath) {
        Song[] songs = new Song[376];
        File inputFile = new File(filePath);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"))) {
            String line;
        	for (int i = 0; (line = br.readLine()) != null; ++i) {
                Song song = new Song(line);
                // You have to create Song object using fieldValues and store
                // the Song object to songs array
                songs[i] = song;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
        return songs;
    }

    /*
     * A function to print statistics about Song Title, Singer, Genre, ... You
     * have to implement it
     */
    
//    DEV :: 최대한 유사한 장르별로 묶어 장르별로 분류
//    DEV :: 리턴하는 결과값이 없이 그냥 화면에 print
    public void printSongStatistics() {
    	System.out.println("Song Statistics");
    	System.out.println("Song count per genre");
    	
//    	DEV :: ArrayList는 배열인데 동적배열로 크기가 정해지지 않은 데이터에 대해 편하게 쓸수 있어서 적용했습니다.
    	ArrayList<String> distinct_genre = new ArrayList<String>();
    	
    	for(int i = 0; i < this.songs.length-1; i++)
    	{
    		String modify_genre = this.songs[i].getBigGenre();
    		
    		if(distinct_genre.size() == 0)
    			distinct_genre.add(modify_genre);
    		else
    		{
    			boolean exist = false;
    			for(int j = 0; j < distinct_genre.size(); j++)
    			{
    				if(modify_genre.equals(distinct_genre.get(j)))
    				{
    					exist = true;
    					break;
    				}
    			}
    			
    			if(exist == false) 
    				distinct_genre.add(modify_genre);
    		}
    	}
    	
    	int sum = 0;
    	for(int i = 0; i < distinct_genre.size(); i++)
    	{
    		System.out.print(i + " :: " + distinct_genre.get(i));
    		int count = 0;
    		for(int j = 0; j < this.songs.length-1; j++)
        	{
    			
        		if(songs[j].getBigGenre().equals(distinct_genre.get(i)))
        			count++;
        	}
    		sum += count;
    		System.out.println(" :: " + count);
    	}
    	System.out.println(sum);
    }

    /*
     * A function to print basic statistics for each student You have to
     * implement it
     */
    // DEV :: 학생별 좋아하는 장르
//    DEV :: 결과값 return 없이 바로 print한다.
    public void printStudentStatistics() {
    	System.out.println("Student Statistics");
    	System.out.println("favorite genre per student");
    	
    	ArrayList<String> student = new ArrayList<String>();
    	
//    	DEV :: 학생들을 ArrayList에 추가
    	for(int i = 0; i < this.songs.length-1; i++)
    	{
    		if(!student.contains(this.songs[i].getStudent()))
    			student.add(this.songs[i].getStudent());
    	}
    	
    	for(String temp : student)
    		System.out.println(temp + " :: " + getFavoriteGenre(temp));
    }
    
//    DEV :: 가장 좋아하는 장르를 찾아주는 method
//    DEV :: 추천 기능을 개발할때 가장 많이 쓰는 기능중 하나라서 Recommend의 메소드로 빼놓고
//    DEV :: 상속받은 클래스 내부에서도 사용가능하게 함.
    public String getFavoriteGenre(String studentId)
    {
    	ArrayList<String> arr_genre = new ArrayList<String>();
    	int max = -1;
    	String fav_genre = "";
    	
    	for(int i = 0; i < this.songs.length-1; i++)
    	{
    		if(this.songs[i].getStudent().equals(studentId) && !arr_genre.contains(this.songs[i].getBigGenre()))
    			arr_genre.add(this.songs[i].getBigGenre());
    	}
    	
    	for(String temp : arr_genre)
    	{
    		int count = 0;
	    	for(int i = 0; i < this.songs.length-1; i++)
	    	{
	    		if(this.songs[i].getStudent().equals(studentId) && this.songs[i].getBigGenre().equals(temp))
	    			count++;
	    	}
	    	
	    	if(count >= max)
	    	{
	    		fav_genre = temp;
	    		max = count;
	    	}	
    	}
		return fav_genre;
    }
}
