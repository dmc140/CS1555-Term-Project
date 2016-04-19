
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
	private Connection con;
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
                    String lf;
                    String fl;
                    String ll;
                    System.out.println("Enter a the first name of user a");
                    ff = scan.nextLine();
                    System.out.println("Enter a the last name of user a");
                    lf = scan.nextLine();
                    System.out.println("Enter a the first name of user b");
                    fl = scan.nextLine();
                    System.out.println("Enter a the last name of user b");
                    ll = scan.nextLine();

                    error = initiateFriendship(findId(ff,lf,con),findId(fl,ll,con),con);

                    if(error!=0){
                        System.out.println("Error Initiating Friendship");
                    }
                    else{
                        System.out.println("Friendship Initiated");
                    }
                    break;
                case 3:
                    String ffa;
                    String lfa;
                    String fla;
                    String lla;
                    System.out.println("Enter a the first name of user a");
                    ffa = scan.nextLine();
                    System.out.println("Enter a the last name of user a");
                    lfa = scan.nextLine();
                    System.out.println("Enter a the first name of user b");
                    fla = scan.nextLine();
                    System.out.println("Enter a the last name of user b");
                    lla = scan.nextLine();

                    error = establishFriendship(findId(ffa,lfa,con),findId(fla,lla,con),con);

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
                    String er;
                    System.out.println("Enter a the first name of the user");
                    qw = scan.nextLine();
                    System.out.println("Enter a the last name of the user");
                    er = scan.nextLine();
                    ar = displayFriends(findId(qw,er,con),con);
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
                    String p;
                    String u;
                    int use = 0;
                    int grp = 0;
                    System.out.println("Enter a the first name of the user");
                    o = scan.nextLine();
                    System.out.println("Enter a the last name of the user");
                    p = scan.nextLine();
                    use = findId(o,p,con);
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
                    String pq;
                    String uq;
                    String yq;
                    String sub;
                    String bod;
                    int useq = 0;
                    int usew = 0;
                    System.out.println("Enter a the first name of the receiver");
                    oq = scan.nextLine();
                    System.out.println("Enter a the last name of the receiver");
                    pq = scan.nextLine();
                    System.out.println("Enter a the first name of the sender");
                    uq = scan.nextLine();
                    System.out.println("Enter a the last name of the sender");
                    yq = scan.nextLine();
                    useq = findId(oq,pq,con);
                    usew = findId(uq,yq,con);
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
                    String pqa;
                    String uqa;
                    String yqa;
                    String suba;
                    String boda;
                    int useqa = 0;
                    int usewa = 0;
                    System.out.println("Enter a the first name of the sender");
                    oqa = scan.nextLine();
                    System.out.println("Enter a the last name of the sender");
                    pqa = scan.nextLine();
                    System.out.println("Enter a the name of the group");
                    uqa = scan.nextLine();
                    useqa = findId(oqa,pqa,con);
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
                    String buy;
                    int muy;
                    System.out.println("Enter a the first name of the user");
                    nuy = scan.nextLine();
                    System.out.println("Enter a the last name of the user");
                    buy = scan.nextLine();
                    muy = findId(nuy,buy,con);
                    error = displayMessages(muy,con);
                    if(error!=0) {
                        System.out.println("Error displaying messages");
                    }
                    break;
                case 10:
                    String auy;
                    String suy;
                    int duy;
                    System.out.println("Enter a the first name of the user");
                    auy = scan.nextLine();
                    System.out.println("Enter a the last name of the user");
                    suy = scan.nextLine();
                    duy = findId(auy,suy,con);
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
	public static int findId(String f, String l, Connection sql){

        String ins = "SELECT * FROM users WHERE fname = " + f + " AND lname = " + l + "";
        Statement s = null;
        try {
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(ins);
            rs.next();
            return rs.getInt("user_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    return -1;
}

public static String findName(int x, Connection sql){

    String ins = "SELECT * FROM users WHERE user_id = " + x + "";
    String total = "";
    Statement s = null;
    try {
        s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
        rs.next();
        total = rs.getString("fname");
        total = total + " " + rs.getString("lname");
        return total;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

public static int findGId(String x, Connection sql){

    String ins = "SELECT * FROM groups WHERE gname = " + x + "";
    Statement s = null;
    try {
        s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
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
		
		//utildate = new SimpleDateFormat("ddMMyyyy").parse(dob.replaceAll("/", ""));
		date = new Date(Calendar.getInstance().getTimeInMillis());
	}
	catch(Exception e){
		e.printStackTrace();
	}
	String ins = "INSERT INTO users (user_ID, name, email, dob) VALUES(" + NEW_USER_ID + ", '" + name + "', '" + email + "', " + date + ")";
    Statement s = null;
    //PreparedStatement prepStatement = sql.prepareStatement(ins);
    
    try {
    	//prepStatement.setInt(1, NEW_USER_ID);
        //prepStatement.setString(2, name);
        //prepStatement.setString(3, email);
        //prepStatement.setDate(4, date);
        s = sql.createStatement();
        s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }

    NEW_USER_ID++;
    return 0;
}

public static int initiateFriendship(int userA, int userB, Connection sql) {

    String ins = "INSERT INTO friendships (user_a,user_b) VALUES(" + userA + "," + userB + ")";
    Statement s = null;

    try {
        s = sql.createStatement();
        s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    return 0;
}

public static int establishFriendship(int userA, int userB, Connection sql){

    String ins = "UPDATE friendships SET pending = 0 WHERE " + userA + " = user_A AND " + userB + " = user_B";
    Statement s = null;

    try {
        s = sql.createStatement();
        s.executeUpdate(ins);
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
    try {
        s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
        if(rs!=null){
            rs.beforeFirst();
            rs.last();
            size = rs.getRow();
            rs.beforeFirst();
        }
        out = new int[size];
        while(rs.next()){
            A = rs.getInt("user_a");
            B = rs.getInt("user_b");
            if(A == userA){
                out[c] = B;
            }
            else{
                out[c] = A;
            }
            c++;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return out;
}

public static int createGroup(String name, String des, int limit, Connection sql){

    String ins = "INSERT INTO groups VALUES(" + NEW_GROUP_ID + "," + name + "," + des + "," + limit + ")";
    Statement s = null;

    try {
        s = sql.createStatement();
        s.executeUpdate(ins);
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
    Statement s = null;
    //cnt <- SELECT COUNT(userID) FROM members WHERE groudID = gid
    String ins = "SELECT COUNT(userID) AS da_count FROM members WHERE gid = " + groupID + " GROUP BY userID";
    try {
        s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
        cnt= rs.getInt("da_count");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    ins = "SELECT limit FROM groups WHERE gid = " + groupID + "";
    try {
        s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
        lmt= rs.getInt("limit");

    } catch (SQLException e) {
        e.printStackTrace();
    }
    if(cnt+1>lmt){
        ins = "INSERT INTO members VALUES(" + userID + "," + groupID + ")";
        try {
            s = sql.createStatement();
            s.executeUpdate(ins);

        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
    else{
        return 1;
    }

    return 0;
}

public static int sendMessageToUser(String subject, String body, int to, int from, Connection sql){

    String ins = "INSERT INTO messages VALUES(" + NEW_MSG_ID + "," + subject + "," + body + "," + to + "," + from + "," + 1 +")";
    // INSERT INTO messages(NEW_MSG_ID,subject,body,from,to,1)

    Statement s = null;

    try {
        s = sql.createStatement();
        s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    NEW_MSG_ID++;
    return 0;
}

public static int sendMessageToGroup(String subject, String body, int to, int from, Connection sql){

    // INSERT INTO messages(NEW_MSG_ID,subject,body,from,to,0)

    String ins = "INSERT INTO messages VALUES(" + NEW_MSG_ID + "," + subject + "," + body + "," + to + "," + from + "," + 0 +")";

    Statement s = null;

    try {
        s = sql.createStatement();
        s.executeUpdate(ins);
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    NEW_MSG_ID++;
    return 0;
}

public static int displayMessages(int user, Connection sql){
    //SELECT * FROM messages WHERE rid = user
    //print neatly
    //SELECT * FROM messages WHERE rid = (
    //  SELECT gid FROM groups WHERE gid = (
    //      SELECT gid FROM members WHERE user_id = user
    //  )
    //)
    //print neatly
    String ins = "SELECT * FROM friendships WHERE " + user + " = rid";
    // Print neatly
    Statement s = null;
    try {
        s = sql.createStatement();
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
            "SELECT gid FROM members WHERE user_id = " + user +
            "))";

    try {
        s = sql.createStatement();
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

public static int displayNewMessages(int user, Connection sql){
    //SELECT * FROM messages WHERE rid = user
    //print neatly
    //SELECT * FROM messages WHERE rid = (
    //  SELECT gid FROM groups WHERE gid = (
    //      SELECT gid FROM members WHERE user_id = user
    //  )
    //)
    //print neatly

    String ins = "SELECT * FROM friendships WHERE " + user + " = rid AND date_sent > (" +
            "SELECT last_login FROM members WHERE user_id = " + user + ")";
    // Print neatlyStatement s = null;\
    Statement s = null;
    try {
        s = sql.createStatement();
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
            "SELECT gid FROM members WHERE user_id = " + user +
            ")) AND date_sent > (" +
            "SELECT last_login FROM members WHERE user_id = " + user + ")";

    try {
        s = sql.createStatement();
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
        String ins = "SELECT * FROM users WHERE fname LIKE '%'" + all[i] + "'%' OR lname '%'" + all[i] + "'%'";
        Statement s = null;
        try {
            s = sql.createStatement();
            ResultSet rs = s.executeQuery(ins);
            while(rs.next()){
                System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
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
			  facespace.run();

		}
		catch(Exception ex) {
		   System.out.println("Error: unable to load driver class!");
		   System.exit(1);
		}
	}
}
