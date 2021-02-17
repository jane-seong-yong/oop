package kr.ac.kookmin.cs.oop.project.recommender.impl;

import java.util.ArrayList;

import kr.ac.kookmin.cs.oop.project.model.Song;
import kr.ac.kookmin.cs.oop.project.recommender.Recommender;

/*
 * A song recommender based on the students' preference similarity
 */
public class SimilarStudentRecommender extends Recommender {

    public SimilarStudentRecommender(String filePath) {
        super(filePath);
    }

    
//    DEV :: 비슷한 장르를 좋아하는 학생이 듣는 장르가 일치하는 노래 추천
    @Override
    public Song[] recommend(String studentId) {
//        for (Song song : this.songs) {
//        }
    	ArrayList<String> student = new ArrayList<String>();
    	ArrayList<String> similarStudent = new ArrayList<String>();
    	ArrayList<Song> similarSong = new ArrayList<Song>();
    	
    	String fav_genre = getFavoriteGenre(studentId);
    	Song resultSong[] = new Song[1];
    	
    	
//    	DEV :: 전체를 가져오되 자기자신은 빼야함
    	for(int i = 0; i < this.songs.length-1; i++)
    	{
    		if(!student.contains(this.songs[i].getStudent()) && !this.songs[i].getStudent().equals(studentId))
    			student.add(this.songs[i].getStudent());
    	}
    	
    	for(String temp : student)
    	{
    		if(getFavoriteGenre(temp).equals(fav_genre))
    			similarStudent.add(temp);
    	}
    	
    	if(similarStudent.size() == 0)
    	{
    		System.out.println("SIZE ====  0");
    		
    		for(int i = 0; i < this.songs.length-1; i++)
    		{
    			if(this.songs[i].getBigGenre().equals(fav_genre) && !this.songs[i].getStudent().equals(studentId))
    			{
    				resultSong[0] = this.songs[i];
    				
    				System.out.println("similarStudent recommend!");
    	        	System.out.println("recommend :: " + this.songs[i].toString());
    				break;
    			}
    		}
    		return resultSong;
    	}
    	else
    	{
    		for(String student_id : similarStudent)
    		{
	    		for(int i = 0; i < this.songs.length-1; i++)
	        	{
	    			if(this.songs[i].getStudent().equals(student_id) && this.songs[i].getBigGenre().equals(fav_genre))
	    				similarSong.add(this.songs[i]);
	        	}
    		}
    		
    		double rand_base = Math.random();
    		int rand = (int)(rand_base * similarSong.size());
    		resultSong[0] = similarSong.get(rand);
    		
    		System.out.println("similarStudent recommend!");
        	System.out.println("recommend :: " + similarSong.get(rand).toString());
    		
    		return resultSong;
    	}
    }
}
