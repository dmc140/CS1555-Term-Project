
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
//import oracle.jdbc.*;
public class FaceSpace {
	static int NEW_USER_ID = 0;
	static int NEW_GROUP_ID = 0;
	static int NEW_MSG_ID = 0;
	private static Connection con;
	

	public FaceSpace(Connection con){
		this.con = con;
	}
	public void run(){
		System.out.println("Connected.");
		/*
		try{
			
		String trigger = "CREATE OR REPLACE TRIGGER drop_user BEFORE DELETE ON users FOR EACH ROW "
				+ "BEGIN DELETE FROM members WHERE u_ID=users.user_id; END;/ ";
		PreparedStatement prepStatement = con.prepareStatement(trigger);
		prepStatement.executeUpdate();
		}
		catch(Exception e){
			System.out.println("Error: " + e);
			System.exit(1);
		}
		*/
		int choice = 1;
        int error;
        //Connection conn = null;
        Scanner scan = new Scanner(System.in);
        while(choice!=0){
            System.out.println("Please select an action by the number:");
            System.out.println("1. Create User");
            System.out.println("2. Initiate Friendship");
            System.out.println("3. Establish Friendship");
            System.out.println("4. Display Friends");
            System.out.println("5. Create Group");
            System.out.println("6. Add to Group");
            System.out.println("7. Send Message to User");
            System.out.println("8. Send Message to Group ");
            System.out.println("9. Display Messages");
            System.out.println("10. Display New Messages");
            System.out.println("11. Search for User");
            System.out.println("12. Three Degrees");
            System.out.println("13. Top Messagers");
            System.out.println("14. Drop User");
            System.out.println("0. Exit");
            choice = scan.nextInt();
            scan.nextLine();
            switch(choice){
                case 0:
                    break;
                case 1:
                    String n;
                    String e;
                    String d;
                    System.out.println("Enter a name");
                    n = scan.nextLine();
                    //System.out.println(n);
                    System.out.println("Enter a email");
                    e = scan.nextLine();
                    System.out.println("Enter a date of birth");
                    d = scan.nextLine();

                    error = createUser(n,e,d,con);

                    if(error!=0){
                        System.out.println("Error Creating User");
                    }
                    else{
                        System.out.println("User Created");
                    }
                    break;
                case 2:
                    String ff;
                    String fl;
                    System.out.println("Enter a the name of user a");
                    ff = scan.nextLine();
                    System.out.println("Enter a the name of user b");
                    fl = scan.nextLine();

                    error = initiateFriendship(findId(ff,con),findId(fl,con),con);

                    if(error!=0){
                        System.out.println("Error Initiating Friendship");
                    }
                    else{
                        System.out.println("Friendship Initiated");
                    }
                    break;
                case 3:
                    String ffa;
                    String fla;
                    System.out.println("Enter a the name of user a");
                    ffa = scan.nextLine();
                    System.out.println("Enter a the name of user b");
                    fla = scan.nextLine();

                    error = establishFriendship(findId(ffa,con),findId(fla,con),con);

                    if(error!=0){
                        System.out.println("Error Establishing Friendship");
                    }
                    else{
                        System.out.println("Friendship Established");
                    }
                    break;
                case 4:
                    int[] ar;
                    String qw;
                    System.out.println("Enter a the name of the user");
                    qw = scan.nextLine();
                    ar = displayFriends(findId(qw,con),con);
                    for(int i=0; i<ar.length;i++){
                        System.out.println(findName(ar[i],con));
                    }
                    break;
                case 5:
                    String gn;
                    String des;
                    int lim = 0;
                    System.out.println("Enter a the name of the group");
                    gn = scan.nextLine();
                    System.out.println("Enter a the des of the group");
                    des = scan.nextLine();
                    System.out.println("Enter a the limit of the group");
                    lim = scan.nextInt();

                    error = createGroup(gn,des,lim,con);

                    if(error!=0){
                        System.out.println("Error Creating Group");
                    }
                    else{
                        System.out.println("Group Created");
                    }
                    break;
                case 6:
                    String o;
                    String u;
                    int use = 0;
                    int grp = 0;
                    System.out.println("Enter a the name of the user");
                    o = scan.nextLine();
                    use = findId(o,con);
                    System.out.println("Enter a the name of the group");
                    u = scan.nextLine();
                    grp = findGId(u,con);
                    error = addToGroup(use,grp,con);

                    if(error!=0){
                        System.out.println("Error Adding to Group");
                    }
                    else{
                        System.out.println("Added to Group");
                    }
                    break;
                case 7:
                    String oq;
                    
                    String uq;
                    String sub;
                    String bod;
                    int useq = 0;
                    int usew = 0;
                    System.out.println("Enter a the name of the receiver");
                    oq = scan.nextLine();
                    System.out.println("Enter a the name of the sender");
                    uq = scan.nextLine();
                    useq = findId(oq,con);
                    usew = findId(uq,con);
                    System.out.println("Enter a the subject of the message");
                    sub = scan.nextLine();
                    System.out.println("Enter a the body of the message");
                    bod = scan.nextLine();
                    error = sendMessageToUser(sub,bod,useq,usew,con);
                    if(error!=0){
                        System.out.println("Error sending message");
                    }
                    else{
                        System.out.println("Message Sent");
                    }
                    break;
                case 8:
                    String oqa;
                    
                    String uqa;
                    String yqa;
                    String suba;
                    String boda;
                    int useqa = 0;
                    int usewa = 0;
                    System.out.println("Enter a the name of the sender");
                    oqa = scan.nextLine();
                    System.out.println("Enter a the name of the group");
                    uqa = scan.nextLine();
                    useqa = findId(oqa,con);
                    usewa = findGId(uqa,con);
                    System.out.println("Enter a the subject of the message");
                    suba = scan.nextLine();
                    System.out.println("Enter a the body of the message");
                    boda = scan.nextLine();
                    error = sendMessageToGroup(suba,boda,usewa,useqa,con);
                    if(error!=0){
                        System.out.println("Error sending message");
                    }
                    else{
                        System.out.println("Message Sent");
                    }
                    break;
                case 9:
                    String nuy;
                    int muy;
                    System.out.println("Enter a the name of the user");
                    nuy = scan.nextLine();
                    muy = findId(nuy,con);
                    error = displayMessages(muy,con);
                    if(error!=0) {
                        System.out.println("Error displaying messages");
                    }
                    break;
                case 10:
                    String auy;
                    String suy;
                    int duy;
                    System.out.println("Enter a the name of the user");
                    auy = scan.nextLine();
                    duy = findId(auy,con);
                    error = displayNewMessages(duy,con);
                    if(error!=0) {
                        System.out.println("Error displaying messages");
                    }
                    break;
                case 11:
                    String srch;
                    System.out.println("Enter a the search string");
                    srch = scan.nextLine();
                    error = searchForUser(srch,con);
                    if(error!=0) {
                        System.out.println("Error searching");
                    }
                    break;
                case 12:

                    break;
                case 13:

                    break;
                case 14:

                    break;
                default:
                    System.out.println("Error");
                    break;
            }
        }
	}
	public static int getUIDM(Connection sql){
		int max = 0;
		String ins = "SELECT MAX(user_ID) AS mx FROM users";
		try{
			Statement s = sql.createStatement();
			ResultSet rs = s.executeQuery(ins);
			while(rs.next()){
				max = rs.getInt("mx");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return max;
	}
	public static int getGIDM(Connection sql){
		int max = 0;
		String ins = "SELECT MAX(g_ID) AS mx FROM groups";
		PreparedStatement s = null;
		try{
			s = sql.prepareStatement(ins);
			ResultSet rs = s.executeQuery(ins);
			while(rs.next()){
				max = rs.getInt("mx");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return max;
	}
	public static int getMIDM(Connection sql){
		int max = 0;
		String ins = "SELECT MAX(msg_ID) AS mx FROM messages";
		PreparedStatement s = null;
		try{
			s = sql.prepareStatement(ins);
			ResultSet rs = s.executeQuery(ins);
			while(rs.next()){
				max = rs.getInt("mx");
			}
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		return max;
	}
	public static int findId(String f, Connection sql){

        String ins = "SELECT * FROM users WHERE name= ?";
        PreparedStatement s = null;
        try {
            s = sql.prepareStatement(ins);
            s.setString(1, f);
            ResultSet rs = s.executeQuery();
            rs.next();
            return rs.getInt("user_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return -1;
}

public static String findName(int x, Connection sql){

    String ins = "SELECT * FROM users WHERE user_ID = " + x;
    String total = "";
    Statement s = null;
    try {
        s = sql.createStatement();
        //s.setInt(1, x);
        ResultSet rs = s.executeQuery(ins);
        rs.next();
        total = rs.getString("name");
        return total;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

public static int findGId(String x, Connection sql){

    String ins = "SELECT * FROM groups WHERE gname = ?";
    PreparedStatement s = null;
    try {
    	s = sql.prepareStatement(ins);
        s.setString(1, x);
        ResultSet rs = s.executeQuery();
        rs.next();
        return rs.getInt("g_id");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1;
}

public static int createUser(String name, String email, String dob, Connection sql) {
	//Calendar calendar = Calendar.getInstance();
	//java.util.Date utildate = null;
	java.sql.Date date = null;
	try{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsed = format.parse(dob);
		date = new java.sql.Date(parsed.getTime());
		//utildate = new SimpleDateFormat("ddMMyyyy").parse(dob.replaceAll("/", ""));
		//date = new Date((Calendar.getInstance().getTime()).getTime());
	}
	catch(Exception e){
		e.printStackTrace();
	}
	String ins = "INSERT INTO users (user_ID, name, email, dob) VALUES(?, ?, ?, ?)";
    Statement s = null;
    
    
    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, NEW_USER_ID);
        prepStatement.setString(2, name);
        prepStatement.setString(3, email);
        prepStatement.setDate(4, date);
        prepStatement.executeUpdate();
        //s = sql.createStatement();
        //s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }

    NEW_USER_ID++;
    return 0;
}

public static int initiateFriendship(int userA, int userB, Connection sql) {

    String ins = "INSERT INTO friendships (user_a,user_b) VALUES(?, ?)";
    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, userA);
        prepStatement.setInt(2, userB);
        prepStatement.executeUpdate();
        //s = sql.createStatement();
        //s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    return 0;
}

public static int establishFriendship(int userA, int userB, Connection sql){

    String ins = "UPDATE friendships SET pending = 0 WHERE ? = user_A AND ? = user_B";
    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, userA);
        prepStatement.setInt(2, userB);
        prepStatement.executeUpdate();
        //s = sql.createStatement();
        //s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    return 0;
}

public static int[] displayFriends(int userA, Connection sql){
    String ins = "SELECT * FROM friendships WHERE " + userA + " = user_A OR " + userA + " = user_B";
    // Print neatlyStatement s = null;\
    Statement s = null;
    int size = 0;
    int c = 0;
    int A = 0;
    int B = 0;
    int[] out = null;
    ArrayList<Integer> out2 = new ArrayList<Integer>();
    try {
        s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
        
        
        while(rs.next()){
            A = rs.getInt("user_a");
            B = rs.getInt("user_b");
            if(A == userA){
                out2.add(B);
            }
            else{
                out2.add(A);
            }
            c++;
        }
        size = out2.size();
        out = new int[size];
        for (int i = 0; i < size; i++){
        	out[i] = out2.get(i);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return out;
}

public static int createGroup(String name, String des, int limit, Connection sql){

    String ins = "INSERT INTO groups VALUES(?, ?, ?, ?)";
    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, NEW_GROUP_ID);
        prepStatement.setString(2, name);
        prepStatement.setString(3, des);
        prepStatement.setInt(4, limit);
        prepStatement.executeUpdate();
        //s = sql.createStatement();
        //s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }

    NEW_GROUP_ID++;
    return 0;
}

public static int addToGroup(int userID, int groupID, Connection sql){
    int cnt = 0;
    int lmt = 0;
    PreparedStatement s = null;
    //cnt <- SELECT COUNT(userID) FROM members WHERE groudID = gid
    String ins = "SELECT COUNT(u_ID) AS da_count FROM members WHERE g_ID = ? GROUP BY u_ID";
    try {
    	s = sql.prepareStatement(ins);
        s.setInt(1, groupID);
        ResultSet rs = s.executeQuery();
        if(rs != null){
        	rs.next();
            cnt = rs.getInt("da_count");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    ins = "SELECT glimit FROM groups WHERE g_ID = ?";
    try {
    	s = sql.prepareStatement(ins);
        s.setInt(1, groupID);
        ResultSet rs = s.executeQuery();
        if (rs != null){
        	rs.next();
            lmt= rs.getInt("glimit");
        }
        

    } catch (SQLException e) {
        e.printStackTrace();
    }
    System.out.println(cnt);
    System.out.println(lmt);
    if((cnt+1) <= lmt){
        ins = "INSERT INTO members VALUES(?, ?)";
        try {
        	s = sql.prepareStatement(ins);
            s.setInt(1, userID);
            s.setInt(2, groupID);
            s.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
    else{
    	System.out.println("this is the error");
        return 1;
    }

    return 0;
}

public static int sendMessageToUser(String subject, String body, int to, int from, Connection sql){

    String ins = "INSERT INTO messages (msg_ID, subject, body, sender, r_ID, is_user) VALUES(?, ?, ?, ?, ?, ?)";
    // INSERT INTO messages(NEW_MSG_ID,subject,body,from,to,1)

    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, NEW_MSG_ID);
        prepStatement.setString(2, subject);
        prepStatement.setString(3, body);
        prepStatement.setInt(4, from);
        prepStatement.setInt(5, to);
        prepStatement.setInt(6, 1);
    	prepStatement.executeUpdate();
        //s = sql.createStatement();
        //s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    NEW_MSG_ID++;
    return 0;
}

public static int sendMessageToGroup(String subject, String body, int to, int from, Connection sql){

    // INSERT INTO messages(NEW_MSG_ID,subject,body,from,to,0)

    String ins = "INSERT INTO messages (msg_ID, subject, body, sender, r_ID, is_user) VALUES(?, ?, ?, ?, ?, ?)";

    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, NEW_MSG_ID);
        prepStatement.setString(2, subject);
        prepStatement.setString(3, body);
        prepStatement.setInt(4, from);
        prepStatement.setInt(5, to);
        prepStatement.setInt(6, 0);
    	prepStatement.executeUpdate();
        //s = sql.createStatement();
        //s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    NEW_MSG_ID++;
    return 0;
}

public static int displayMessages(int user, Connection sql){
	/*
	String ins3 = "SELECT * FROM messages";
	PreparedStatement s3 = null;
	try{
		s3 = sql.prepareStatement(ins3);
		ResultSet rs3 = s3.executeQuery();
		while(rs3.next()){
			System.out.println("To: " + findName(rs3.getInt("r_ID"), sql));
            System.out.println("From: " + findName(rs3.getInt("sender"), sql));
            System.out.println("Subject: " + rs3.getString("subject"));
            System.out.println("Is User?" + rs3.getInt("is_user"));
            System.out.println("Body: " + rs3.getString("body") + "\n\n\n");
            
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	*/
    //SELECT * FROM messages WHERE rid = user
    //print neatly
    //SELECT * FROM messages WHERE rid = (
    //  SELECT gid FROM groups WHERE gid = (
    //      SELECT gid FROM members WHERE user_id = user
    //  )
    //)
    //print neatly
    String ins = "SELECT * FROM messages WHERE " + user + " = r_ID AND is_user = 1";
    String ins2 = "";
    // Print neatly
    Statement s = null;
    try {
        s = sql.createStatement();
        //s.setInt(1, user);
        ResultSet rs = s.executeQuery(ins);
        while(rs.next()){
            System.out.println("To: " + findName(user, sql));
            System.out.println("From: " + findName(rs.getInt("sender"), sql));
            System.out.println("Subject: " + rs.getString("subject"));
            System.out.println("Body: " + rs.getString("body") + "\n\n\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    ins = "SELECT g_ID FROM members WHERE u_ID = " + user;
    ArrayList<Integer> ar = new ArrayList<Integer>();
    
    

    try {
    	s = sql.createStatement();
        //s.setInt(1, user);
        ResultSet rs = s.executeQuery(ins);
        while(rs.next()){
        	ar.add(rs.getInt("g_ID")); 
        }
        for (int i = 0; i < ar.size(); i++){
    		ins2 = "SELECT * FROM messages WHERE r_ID = ? AND is_user = 0";
    		PreparedStatement s2 = sql.prepareStatement(ins2);
    		s2.setInt(1, ar.get(i));
    		ResultSet rs2 = s2.executeQuery();
    		while(rs2.next()){
    			System.out.println("To: " + findName(user, sql));
                System.out.println("From: " + findName(rs2.getInt("sender"), sql));
                System.out.println("Subject: " + rs2.getString("subject"));
                System.out.println("Body: " + rs2.getString("body") + "\n\n\n");
    		}
    	}

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public static int displayNewMessages(int user, Connection sql){
    //SELECT * FROM messages WHERE rid = user
    //print neatly
    //SELECT * FROM messages WHERE rid = (
    //  SELECT gid FROM groups WHERE gid = (
    //      SELECT gid FROM members WHERE user_id = user
    //  )
    //)
    //print neatly

    String ins = "SELECT * FROM friendships WHERE ? = rid AND date_sent > (" +
            "SELECT last_login FROM members WHERE user_id = ?)";
    // Print neatlyStatement s = null;\
    PreparedStatement s = null;
    try {
    	s = sql.prepareStatement(ins);
        s.setInt(1, user);
        ResultSet rs = s.executeQuery(ins);
        while(rs.next()){
            System.out.println("To: " + user);
            System.out.println("From: " + rs.getString("sender"));
            System.out.println("Subject: " + rs.getString("subject"));
            System.out.println("Body: " + rs.getString("body") + "\n\n\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    ins = "SELECT * FROM messages WHERE rid = (" +
            "SELECT gid FROM groups WHERE gid = (" +
            "SELECT gid FROM members WHERE user_id = ?)) AND date_sent > (" +
            "SELECT last_login FROM members WHERE user_id = ?)";

    try {
    	s = sql.prepareStatement(ins);
        s.setInt(1, user);
        ResultSet rs = s.executeQuery(ins);
        while(rs.next()){
            System.out.println("To: " + user);
            System.out.println("From: " + rs.getString("sender"));
            System.out.println("Subject: " + rs.getString("subject"));
            System.out.println("Body: " + rs.getString("body") + "\n\n\n");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}

public static int searchForUser(String st, Connection sql){
    String[] all;
    all = st.split("[^\\w']");
    for(int i=0; i<all.length;i++){
        String ins = "SELECT * FROM users WHERE name LIKE '%' ? '%'";
        PreparedStatement s = null;
        try {
        	s = sql.prepareStatement(ins);
            s.setString(1, st);
            ResultSet rs = s.executeQuery(ins);
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return 0;
}
	
	public static void threeDegrees(int userA, int userB, Connection sql){
		boolean isFriend = false;
		boolean noPath = true;
		ArrayList<Integer> res = new ArrayList<Integer>();
		int[] c = displayFriends(userA, sql);
		for(int i = 0; i < c.length; i++){
			if (userB == c[i]){
				isFriend = true;
				continue;
			}
			
			int[] d = displayFriends(c[i], sql);
			for (int j = 0; j < d.length; j++){
				if (userB == d[j]){
					res.add(c[i]);
					continue;
				}
				int[] e = displayFriends(d[j], sql);
				for (int k = 0; k < e.length; k++){
					if (userB == e[k]){
						noPath = false;
						System.out.println(userA + " -> " + c[i] + " -> " + d[j] + " -> " + userB);
					}
				}
			}
		}
		if (!res.isEmpty()){
			noPath = false;
			for (int i = 0; i < res.size(); i++){
				System.out.println(userA + " -> " + res.get(i) + " -> " + userB);
			}
		}

		if (isFriend){
			System.out.print(userA + " -> " + userB);
			noPath = false;
		}
		if (noPath){
			System.out.println("No path exists between these two users.");
		}
		
	}
	
	public static void topMessages(int x, int k){
		Calendar calendar = Calendar.getInstance();
		//java.sql.Date curr_date = new java.sql.Date(calendar.getTime().getTime());
		calendar.add(calendar.MONTH, x);
		java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
		//sql statement group by sender or r_id
		for (int i = 0; i < k; i++){
			
		}
	}
	
	public static void dropUser(int id, Connection sql){
		try{
			String user_query = "DELETE FROM users WHERE user_ID="+id;
			PreparedStatement prepStatement = sql.prepareStatement(user_query);
			prepStatement.executeUpdate();
			String msg_query = "DELETE FROM messages WHERE sender="+id+" AND NOT EXISTS("
					+ "SELECT user_ID FROM users WHERE user_id=r_ID) OR r_ID="+id+"AND NOT EXISTS("
					+ "SELECT user_id FROM users WHERE user_id=sender)";
			PreparedStatement prepStatement2 = sql.prepareStatement(msg_query);
			prepStatement2.executeUpdate();
		}
		catch(Exception e){
			System.out.println("Error: " + e);
			System.exit(1);
		}
		
	}
	
	public static void main(String[] args){
		
		Connection con;
		try {
			  Driver myDriver = new oracle.jdbc.driver.OracleDriver();
			  DriverManager.registerDriver( myDriver );
			  con = DriverManager.getConnection("jdbc:oracle:thin:@class3.cs.pitt.edu:1521:dbclass", "mik92", "89iokl-zx");
			  FaceSpace facespace = new FaceSpace(con);
			  
			  NEW_USER_ID = getUIDM(con) + 1;
			  NEW_GROUP_ID = getGIDM(con) + 1;
			  NEW_MSG_ID = getMIDM(con) + 1;
			  
			  facespace.run();

		}
		catch(Exception ex) {
		   System.out.println("Error: unable to load driver class!");
		   System.exit(1);
		}
	}
}
