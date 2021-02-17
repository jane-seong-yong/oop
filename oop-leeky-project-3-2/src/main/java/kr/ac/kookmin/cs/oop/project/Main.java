package kr.ac.kookmin.cs.oop.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import kr.ac.kookmin.cs.oop.project.model.Song;
import kr.ac.kookmin.cs.oop.project.recommender.Recommender;
import kr.ac.kookmin.cs.oop.project.recommender.impl.RandomRecommender;
import kr.ac.kookmin.cs.oop.project.recommender.impl.SimilarSongRecommender;
import kr.ac.kookmin.cs.oop.project.recommender.impl.SimilarStudentRecommender;

public class Main {

	private final String[] studentIds;
	// declare recommendedSongs field. It should store recommended songs for each students
	//	DEV :: 원래 이 함수를 추가해서 여기에 추천노래를 담기를 원하셨던것 같은데 그렇게 하면 제 입장에서는 구현이 좀 복잡하네요.. ㅠㅠㅠ 설계 자체의 방향이 달라서  전 추가하지 않았습니다.
	//	private Song[] recommendedSongs;
	
	private Recommender randMusicRecommender;
	private Recommender similarSongRecommender;
	private Recommender similarStudentRecommender;
	

	public Main(String filePath) {
		studentIds = extractStudentsIds(filePath);
		randMusicRecommender = new RandomRecommender(filePath);
		similarSongRecommender = new SimilarSongRecommender(filePath);
		similarStudentRecommender = new SimilarStudentRecommender(filePath);
	}

	/*
	 * From the input file path that contains information about the song
	 * preference list, it should extract unique students IDs and it should
	 * return it as a String array. There are 36 unique student IDs and you
	 * should return only the unique IDs.
	 */
	
//	DEV :: extractStudentsIds() 함수 구현
	private static String[] extractStudentsIds(String filePath) {
		File inputFile = new File(filePath);
		String[] studentIds = new String[37];
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile), "UTF-8"))) {
			for (String line; (line = br.readLine()) != null;) {
//				DEV :: 원래 아래 system.out.println() 부분 두개가 있었는데 주석처리 했습니다.
				//                System.out.println(line);
				String[] fieldValues = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				//                System.out.println(fieldValues[0]);
				// You have to extract the id that is the first element in
				// the fieldValues and store unique ones to studentIds String
				// array
				
//				DEV :: ID추출을 위한 코드
//				DEV :: 각 ID를 가져와서 배열에 존재하지 않는 아이디만 그 다음 순서에 추가합니다.
				Boolean exist = false;
				int arr_cnt = 0;

				for(int i = 0; i < studentIds.length; i++)
					if(studentIds[i] != null) arr_cnt++;

				if(arr_cnt == 0)
					studentIds[0] = fieldValues[0];
				else
				{
					for(int i = 0; i < arr_cnt; i++)
					{
						if(studentIds[i].equals(fieldValues[0]))
						{
							exist = true;
							break;
						}
					}
					if(exist == false)
						studentIds[arr_cnt] = fieldValues[0];
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		// for check studentIds
//		DEV :: 각 배열에 들어가 있는 ID를 체크하고 싶으시면 아래 주석 해제하시면 됩니다.
//		for(int i = 0; i < studentIds.length; i++)
//			System.out.println(i + " :: " + studentIds[i]);

		return studentIds;
	}

	/*
	 * A function to print out basic statistics about song and students
	 */
	public void printStatistics() {
//		musicRecommender.printSongStatistics();
//		musicRecommender.printStudentStatistics();
		
//		similarSongRecommender.printSongStatistics();
		similarSongRecommender.printStudentStatistics();
		similarSongRecommender.printSongStatistics();
		
	}

	/*
	 * A function that calls recommend function that is implemented in different
	 * classes
	 */
	public void recommend() {
		System.out.println("It's Main's recommend!!!");
		for (String student : studentIds) {
//			Song[] recommendedSongs = musicRecommender.recommend(student);
		}
	}

	public static void main(String[] args) {
		Main runner = new Main("resource/song-list.csv");
		runner.printStatistics();
//		runner.recommend();

		while(true)
		{
			try {
				Scanner sc = new Scanner(System.in);
				System.out.println("Welcome! -----------------");
				System.out.println("1. RandomRecommend -------");
				System.out.println("2. SimilarSongRecommend---");
				System.out.println("3. SimilarStudentRecommend");
				System.out.print("Recommend Mode? --> ");
				int rec_choice = sc.nextInt();

				if(rec_choice != 1 && rec_choice != 2 && rec_choice !=3 )
				{
					System.out.println("Please type 1,2,3 value!");
					continue;
				}
				
				System.out.println("Please Type User! ---------");
				String user_id = sc.next();
				
				Boolean exist = false;
				for (String studentId : runner.studentIds)
				{
					if(studentId.equals(user_id))
					{
						exist = true;
						break;
					}
				}
				
				if(exist == false)
				{
					System.out.println("ID is not correct!");
					continue;
				}
				else
				{
					System.out.println("Good input");
					
					switch(rec_choice) {
					case 1 :
						System.out.println("You Chose RandomRecommend!!");
						runner.randMusicRecommender.recommend(user_id);
						break;
					case 2 :
						System.out.println("You Chose SimilarSongRecommend!!");
						runner.similarSongRecommender.recommend(user_id);
						break;
					case 3 :
						System.out.println("You Chose SimilarStudentRecommend!!");
						runner.similarStudentRecommender.recommend(user_id);
						break;
					}
				}
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("FAILED CAUSE BY ::" + e);
//				e.printStackTrace();
				System.out.println("Please Input Correct Value!");
			}
		}
	}
}
