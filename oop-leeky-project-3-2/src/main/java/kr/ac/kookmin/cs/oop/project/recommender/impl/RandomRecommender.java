package kr.ac.kookmin.cs.oop.project.recommender.impl;

import kr.ac.kookmin.cs.oop.project.model.Song;
import kr.ac.kookmin.cs.oop.project.recommender.Recommender;

/*
 * A song recommender that simply relies on random algorithm.
 * Recommend whatever you want to
 */
public class RandomRecommender extends Recommender {

    public RandomRecommender(String filePath) {
        super(filePath);
    }

    /*
     * Write your own recommender module. You can remove the for loop and write
     * your custom logic
     */
//    DEV :: 곡 갯수만큼 랜덤 숫자를 만들어서 
//    DEV :: 해당하는 숫자의 값을 결과로 넘겨준다.
    
    @Override
    public Song[] recommend(String studentId) {
    	
    	double rand_base = Math.random();
    	int rand = (int)(rand_base * this.songs.length-1);
    	
    	System.out.println("input studentId --> " + studentId);
    	System.out.println("random recommend!");
    	System.out.println("recommend :: " + this.songs[rand].toString());
    	
    	Song[] songs = new Song[1];
    	songs[0] = this.songs[rand];

    	return songs;
    }
}
