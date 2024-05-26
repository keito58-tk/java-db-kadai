package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		Connection con = null;
		Statement statement = null;
		ResultSet result = null;
		
		try {
			// データベース接続
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/challenge_java",
					"root",
					"A8*kR@h%L1vZ0&cM"
				);
			System.out.println("データベース接続成功：" + con);
			
			// ステートメント作成
			statement = con.createStatement();
			
			// 武者小路勇気さんの点数データを更新
			System.out.println("レコード更新を実行します");
			String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE name = '武者小路勇気'";
			int rowUpdate = statement.executeUpdate(updateSql);
			System.out.println(rowUpdate + "件のレコードが更新されました");
			
			// scoresテーブルの全レコードを取得し、並べ替える
			System.out.println("数学・英語の点数が高い順に並べ替えました");
			String querySql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC";
			ResultSet result1 = statement.executeQuery(querySql);
			
			// SQLクエリの実行結果を抽出
			int count = 1;
			while (result1.next()) {
				int id = result1.getInt("id");
				String name = result1.getString("name");
				int mathScore = result1.getInt("score_math");
				int englishScore = result1.getInt("score_english");
				System.out.println(count + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + mathScore + "／英語=" + englishScore);
				count++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (statement != null) statement.close();
				if (result != null) result.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
