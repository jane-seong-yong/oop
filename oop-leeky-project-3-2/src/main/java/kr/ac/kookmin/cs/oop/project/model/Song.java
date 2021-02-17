package kr.ac.kookmin.cs.oop.project.model;

/*
 * A class to store the information about each song entry
 * It should contain all the fields that are registered in the song list file
 * You have to declare fields and way to parse comma separated string to the Song class 
 */

// DEV :: Song.java 클래스 구현.
public class Song {
	public String user_id;
	public String title;
	public String artist;
	public String genre;
	public String issue_date;
	public String album_nm;
	public String composer;
	public String producer;
	public String issue_nation;
	public String language;
	public String length;
	public String bigGenre;
	

    public Song(String entry) {
        // field element
        // 사용자 아이디, 제목, 가수, 장르, 발매년도, 앨범명, 작곡가, 프로듀서, 발행국가, 언어, 길이
        String[] fieldValues = entry.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//        System.out.println("class Song :: fieldValues.length :: " + fieldValues.length);
        this.user_id = fieldValues[0];
        this.title = fieldValues[1];
        this.artist = fieldValues[2];
        this.genre = fieldValues[3];
        this.issue_date = fieldValues[4];
        this.album_nm = fieldValues[5];
        this.composer = fieldValues[6];
        this.producer = fieldValues[7];
        this.issue_nation = fieldValues[8];
        this.language = fieldValues[9];
        this.length = fieldValues[10];
        this.bigGenre = this.setBigGenre();
    }

    /*
     * You have to write your custom toString function to print Song information
     */
    @Override
    public String toString() {
    	String result = "";
    	
    	result += this.user_id + " :: ";
    	result += this.title + " :: ";
    	result += this.artist + " :: ";
    	result += this.genre + " :: ";
    	result += this.issue_date + " :: ";
    	result += this.album_nm + " :: ";
    	result += this.composer + " :: ";
    	result += this.producer + " :: ";
    	result += this.issue_nation + " :: ";
    	result += this.language + " :: ";
    	result += this.length;
    	
        return result;
    }
    
    
//    DEV :: Genre값을 받아오는 get함수를 만들었습니다. 그냥 클래스.genre 해서 값을 받아올수 있긴 하지만
//    DEV :: 이러는 편이더 깔끔하기도 하고, 편하기도 해서 이렇게 했습니다.
    public String getGenre() {
    	return this.genre;
    }
    
    public String getTitle()
    {
    	return this.title;
    }
    
    public String getArtist()
    {
    	return this.artist;
    }
    
    public String setBigGenre()
    {
    	String modify_genre = "";
		modify_genre = this.genre.trim().replace(" ", "").replace("\"", "").toUpperCase();
		
		if(modify_genre.contains("ROCK") || modify_genre.contains("락") || modify_genre.contains("얼터너") || modify_genre.contains("ALTER"))
			modify_genre = "락";
		else if(modify_genre.contains("FOLK") || modify_genre.contains("FORK"))
			modify_genre = "포크";
		else if(modify_genre.contains("랩") || modify_genre.contains("RAP") || modify_genre.contains("HOP"))
			modify_genre = "힙합";
		else if(modify_genre.contains("드라마") || modify_genre.contains("OST") || modify_genre.contains("MOVIE"))
			modify_genre = "OST";
		else if(modify_genre.contains("POP") || modify_genre.contains("팝"))
			modify_genre = "팝";
		else if(modify_genre.contains("인디"))
			modify_genre = "인디";
		else if(modify_genre.contains("BALLAD") || modify_genre.contains("발라드") || modify_genre.contains("CCM"))
			modify_genre = "발라드";
		else if(modify_genre.contains("알앤비") || modify_genre.contains("R&B"))
			modify_genre = "R&B";
		else if(modify_genre.contains("재즈"))
			modify_genre = "JAZZ";
		else if(modify_genre.contains("댄스") || modify_genre.contains("DANCE") || modify_genre.contains("DISCO"))
			modify_genre = "댄스";
		else if(modify_genre.contains("트로트") || modify_genre.contains("ADULT"))
			modify_genre = "트로트";
		else if(modify_genre.contains("하우스") || modify_genre.contains("HOUSE") || modify_genre.contains("TRANCE"))
			modify_genre = "하우스";
		else if(modify_genre.contains("일렉") || modify_genre.contains("ELEC"))
			modify_genre = "일렉트로닉";
		else if(modify_genre.contains("METAL") || modify_genre.contains("메탈"))
			modify_genre = "메탈";
		else if(modify_genre.contains("뮤지컬") || modify_genre.contains("가곡") || modify_genre.contains("쇼튠"))
			modify_genre = "가곡";
		else if(modify_genre.equals(""))
			modify_genre = "댄스";
		
		return modify_genre;
    }
    
    public String getBigGenre()
    {
    	return this.bigGenre;
    }
    
    public String getStudent()
    {
    	return this.user_id;
    }
}
