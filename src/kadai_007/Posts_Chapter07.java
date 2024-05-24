package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// データベース接続情報
		String url = "jdbc:mysql://localhost/challenge_java";
		String user = "root";
		String password = "A8*kR@h%L1vZ0&cM";
		
		Connection con = null;
		Statement statement = null;
		
		try {
			// データベース接続
			con = DriverManager.getConnection(url, user, password);
			System.out.println("データベース接続成功:" + con);
			
			// SQLクエリを準備
			statement = con.createStatement();  //ステートメント作成
			// データ追加のSQLクエリ
			String sqlInsert ="INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES"
						+ "(1003, '2023-02-08', '昨日の夜は徹夜でした・・', '13'),"
						+ "(1002, '2023-02-08', 'お疲れ様です！', '12'),"
						+ "(1003, '2023-02-09', '今日も頑張ります！', '18'),"
						+ "(1001, '2023-02-09', '無理は禁物ですよ！', '17'),"
						+ "(1002, '2023-02-10', '明日から連休ですね！', '20');";
			System.out.println("レコード追加を実行します");
			
			// SQLクエリを実行
			int result = statement.executeUpdate(sqlInsert);
			System.out.println(result + "件のレコードが追加されました");
			
			//  データ検索のSQLクエリ
			String sqlSelect = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002";
			ResultSet resultSet = statement.executeQuery(sqlSelect); // 取得系のSQLクエリ
			System.out.println("ユーザーIDが1002のレコードを検索しました");
			
			// SQLクエリの実行結果を抽出
			int count = 1;
			while (resultSet.next()) {
				String postedAt = resultSet.getDate("posted_at").toString();
				String postContent = resultSet.getString("post_content");
				int likes = resultSet.getInt("likes");
				
				System.out.println(count + "件目：投稿日時＝" + postedAt + "／投稿内容＝" + postContent + "／いいね数＝" + likes);
				
				count++;
			}
			
		} catch (SQLException e) {
			System.out.println("エラー発生：" + e.getMessage());
		} finally {
			// 使用したオブジェクトを解放
			if (statement != null) {
				try {statement.close();} catch (SQLException e) {}
			}
			
			if (con != null) {
				try {con.close();} catch (SQLException e) {}
			}
		}
		
	}

}
