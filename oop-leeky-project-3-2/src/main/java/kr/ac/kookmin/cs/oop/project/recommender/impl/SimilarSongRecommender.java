package kr.ac.kookmin.cs.oop.project.recommender.impl;

import java.util.ArrayList;

import kr.ac.kookmin.cs.oop.project.model.Song;
import kr.ac.kookmin.cs.oop.project.recommender.Recommender;

/*
 * A song recommender based on the similarity of songs
 */
public class SimilarSongRecommender extends Recommender {

    public SimilarSongRecommender(String filePath) {
        super(filePath);
    }

//    DEV :: 선택된 studentId가 선호하는 장르의 다른곡을 추천.
//    DEV :: 비슷한 장르의 곡중 랜덤으로 하나를 추천.
//    DEV :: 랜덤을 넣게된 이유는 일단 추천곡은 하나로 하기로 했는데 
//    DEV :: 피치못한 이유가 아니면 계속 같은 곡만 나오게 하기 싫기도 하고, 테스트 할때 유사도 판단하기도 좋았음.
    @Override
    public Song[] recommend(String studentId) {
    	String fav_genre = getFavoriteGenre(studentId);
    	ArrayList<Song> similarSong = new ArrayList<Song>();
    	Song[] resultSong = new Song[1];
    	double rand_base = Math.random();
    	int rand = 0;

    	for(int i = 0; i < this.songs.length-1; i++)
    	{
    		if(this.songs[i].getBigGenre().equals(fav_genre) && 
    				!this.songs[i].getStudent().equals(studentId))
    		{
    			similarSong.add(this.songs[i]);
    		}
    	}
    	
    	rand = (int)(rand_base * similarSong.size());
    	System.out.println("input studentId --> " + studentId);
    	System.out.println("SimilarSong recommend!");
    	System.out.println("recommend :: " + similarSong.get(rand).toString());
    	resultSong[0] = similarSong.get(rand);
        return resultSong;
    }
}
