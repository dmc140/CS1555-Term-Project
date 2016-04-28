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
	
	// Global variables
	static int NEW_USER_ID = 0;
	static int NEW_GROUP_ID = 0;
	static int NEW_MSG_ID = 0;
	private static Connection con;
	

	// Create Connection
	public FaceSpace(Connection con){
		this.con = con;
	}
	
	// The main function that actually runs everything
	public void run(){
		
		// Shows that program has connected to the database
		System.out.println("Connected.");
		
		// A few variables we need
		int choice = 1;
        int error;
        Scanner scan = new Scanner(System.in);
		
		// Controller to implement the system
        while(choice!=0){
			
			// Menu the user sees
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
			
			// The program only works on the machine we used if this is here. We can't figure out why
            scan.nextLine();
			
			// Switch that selects the item the user selected in the menu
            switch(choice){
                case 0:
                    break;
                case 1:
				
					// Variables
                    String n;
                    String e;
                    String d;
					
					// User input
                    System.out.println("Enter a name");
                    n = scan.nextLine();
                    System.out.println("Enter a email");
                    e = scan.nextLine();
                    System.out.println("Enter a date of birth");
                    d = scan.nextLine();

					// Runs the method
                    error = createUser(n,e,d,con);

					// Error checker
                    if(error!=0){
                        System.out.println("Error Creating User");
                    }
                    else{
                        System.out.println("User Created");
                    }
                    break;
					
                case 2:
				
					// Variables
                    String ff;
                    String fl;
					
					// User input
                    System.out.println("Enter a the name of user a");
                    ff = scan.nextLine();
                    System.out.println("Enter a the name of user b");
                    fl = scan.nextLine();

					// Runs the method
                    error = initiateFriendship(findId(ff,con),findId(fl,con),con);

					// Error checker
                    if(error!=0){
                        System.out.println("Error Initiating Friendship");
                    }
                    else{
                        System.out.println("Friendship Initiated");
                    }
                    break;
					
                case 3:
				
					// Variables
                    String ffa;
                    String fla;
					
					// User Input
                    System.out.println("Enter a the name of user a");
                    ffa = scan.nextLine();
                    System.out.println("Enter a the name of user b");
                    fla = scan.nextLine();

					// Runs the method
                    error = establishFriendship(findId(ffa,con),findId(fla,con),con);

					// Error checking
                    if(error!=0){
                        System.out.println("Error Establishing Friendship");
                    }
                    else{
                        System.out.println("Friendship Established");
                    }
                    break;
					
                case 4:
					
					// Variables
					int[] ar;
                    String qw;
					
					// User input
                    System.out.println("Enter a the name of the user");
                    qw = scan.nextLine();
					
					// Runs the method
                    ar = displayFriends(findId(qw,con),con);
					
					// Outputs the results
                    for(int i=0; i<ar.length;i++){
                        System.out.println(findName(ar[i],con));
                    }
                    break;
					
                case 5:
				
					// Variables
                    String gn;
                    String des;
                    int lim = 0;
					
					// User input
                    System.out.println("Enter a the name of the group");
                    gn = scan.nextLine();
                    System.out.println("Enter a the des of the group");
                    des = scan.nextLine();
                    System.out.println("Enter a the limit of the group");
                    lim = scan.nextInt();

					// Runs method
                    error = createGroup(gn,des,lim,con);

					// Error Checker
                    if(error!=0){
                        System.out.println("Error Creating Group");
                    }
                    else{
                        System.out.println("Group Created");
                    }
                    break;
					
                case 6:
				
					// Variables
                    String o;
                    String u;
                    int use = 0;
                    int grp = 0;
					
					// User Input
                    System.out.println("Enter a the name of the user");
                    o = scan.nextLine();
                    use = findId(o,con);
                    System.out.println("Enter a the name of the group");
                    u = scan.nextLine();
                    grp = findGId(u,con);
					
					// Runs the method
                    error = addToGroup(use,grp,con);

					// Error checker
                    if(error!=0){
                        System.out.println("Error Adding to Group");
                    }
                    else{
                        System.out.println("Added to Group");
                    }
                    break;
					
                case 7:
				
					// Variables
                    String oq;
                    String uq;
                    String sub;
                    String bod;
                    int useq = 0;
                    int usew = 0;
					
					// User input
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
					
					// Runs the method
                    error = sendMessageToUser(sub,bod,useq,usew,con);
					
					// Error checker
                    if(error!=0){
                        System.out.println("Error sending message");
                    }
                    else{
                        System.out.println("Message Sent");
                    }
                    break;
					
                case 8:
				
					// Variables
                    String oqa;
                    String uqa;
                    String yqa;
                    String suba;
                    String boda;
                    int useqa = 0;
                    int usewa = 0;
					
					// User input
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
					
					// Runs the method
                    error = sendMessageToGroup(suba,boda,usewa,useqa,con);
					
					// Error checker
                    if(error!=0){
                        System.out.println("Error sending message");
                    }
                    else{
                        System.out.println("Message Sent");
                    }
                    break;
					
                case 9:
					// Variables
                    String nuy;
                    int muy;
					
					// User Input
                    System.out.println("Enter a the name of the user");
                    nuy = scan.nextLine();
                    muy = findId(nuy,con);
					
					// Runs the method
                    error = displayMessages(muy,con);
					
					// Error checker
                    if(error!=0) {
                        System.out.println("Error displaying messages");
                    }
                    break;
					
                case 10:
				
					// Variables
                    String auy;
                    String suy;
                    int duy;
					
					// User input
                    System.out.println("Enter a the name of the user");
                    auy = scan.nextLine();
                    duy = findId(auy,con);
					
					// Runs the method
                    error = displayNewMessages(duy,con);
					
					// Error checker
                    if(error!=0) {
                        System.out.println("Error displaying messages");
                    }
                    break;
					
                case 11:
					
					// Variables
                    String srch;
					
					// User input
                    System.out.println("Enter a the search string");
                    srch = scan.nextLine();
					
					// Runs the method
                    error = searchForUser(srch,con);
					
					// Error checker
                    if(error!=0) {
                        System.out.println("Error searching");
                    }
                    break;
					
                case 12:
				
					// Variables
                	String one;
                	String two;
					
					// User input
                	System.out.println("Enter the first user");
                    one = scan.nextLine();
                    System.out.println("Enter the second user");
                    two = scan.nextLine();
					
					// Runs the method
                	threeDegrees(findId(one, con), findId(two, con), con);

                    break;
					
                case 13:
				
					// Variables
                	int k;
                	int x;
					
					// User input
                	System.out.println("How many users?");
                	k = scan.nextInt();
                	scan.nextLine();
                	System.out.println("How many months?");
                	x = scan.nextInt();
                	scan.nextLine();
					
					// Runs the method
                	topMessagers(x, k, con);
                    break;
					
                case 14:
					
					// Variables
                	String drop;
					
					// User input
                    System.out.println("Enter a name");
                    drop = scan.nextLine();

					// Runs the method
                    error = dropUser(findId(drop, con),con);

					// Error checker
                    if(error!=0){
                        System.out.println("Error Dropping User");
                    }
                    else{
                        System.out.println("User Dropped");
                    }
                    break;
					
                default:
                    System.out.println("Error");
                    break;
            }
        }
	}
	public static int getUIDM(Connection sql){
		
		// Variables
		int max = 0;
		
		// SQL
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
		
		// Variables
		int max = 0;
		
		// SQL
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
		
		// Variables
		int max = 0;
		
		// SQL
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
		
		// SQL
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

	// SQL
    String ins = "SELECT * FROM users WHERE user_ID = " + x;
	
	// Variables
    String total = "";
    Statement s = null;
	
	// SQL
    try {
        s = sql.createStatement();
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

	// SQL
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

public static String findGName(int x, Connection sql){
	
	// SQL
	String ins = "SELECT * FROM groups WHERE g_ID = " + x;
	
	// Variables
    String total = "";
	
	// SQL
    Statement s = null;
    try {
        s = sql.createStatement();
        //s.setInt(1, x);
        ResultSet rs = s.executeQuery(ins);
        rs.next();
        total = rs.getString("gname");
        return total;

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return total;
}

public static int createUser(String name, String email, String dob, Connection sql) {
	
	// SQL
	java.sql.Date date = null;
	try{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date parsed = format.parse(dob);
		date = new java.sql.Date(parsed.getTime());
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
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }

    NEW_USER_ID++;
    return 0;
}

public static int initiateFriendship(int userA, int userB, Connection sql) {

	// SQL
    String ins = "INSERT INTO friendships (user_a,user_b) VALUES(?, ?)";
    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, userA);
        prepStatement.setInt(2, userB);
        prepStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    return 0;
}

public static int establishFriendship(int userA, int userB, Connection sql){

	// SQL
    String ins = "UPDATE friendships SET pending = 0 WHERE ? = user_A AND ? = user_B";
    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, userA);
        prepStatement.setInt(2, userB);
        prepStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    return 0;
}

public static int[] displayFriends(int userA, Connection sql){
	
	// SQL
    String ins = "SELECT * FROM friendships WHERE " + userA + " = user_A OR " + userA + " = user_B";
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
			
			// Picks the user that is not the one we are searching from
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
		
		// Changes from arraylist to array for ease of use with our other methods that user this
        for (int i = 0; i < size; i++){
        	out[i] = out2.get(i);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return out;
}

public static int createGroup(String name, String des, int limit, Connection sql){

	// SQL
    String ins = "INSERT INTO groups VALUES(?, ?, ?, ?)";
    Statement s = null;

    try {
    	PreparedStatement prepStatement = sql.prepareStatement(ins);
    	prepStatement.setInt(1, NEW_GROUP_ID);
        prepStatement.setString(2, name);
        prepStatement.setString(3, des);
        prepStatement.setInt(4, limit);
        prepStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }

    NEW_GROUP_ID++;
    return 0;
}

public static int addToGroup(int userID, int groupID, Connection sql){
	
	// Variables
    int cnt = 0;
    int lmt = 0;
	
	// SQL
	
	// Gets how many users are currently in the group
    PreparedStatement s = null;
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
	
	// Gets the group limit
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
    //System.out.println(cnt);
    //System.out.println(lmt);
	
	// If the limit is not reached, add user
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

	// SQL
    String ins = "INSERT INTO messages (msg_ID, subject, body, sender, r_ID, is_user) VALUES(?, ?, ?, ?, ?, ?)";

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
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    NEW_MSG_ID++;
    return 0;
}

public static int sendMessageToGroup(String subject, String body, int to, int from, Connection sql){

    // SQL
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
    } catch (SQLException e) {
        e.printStackTrace();
        return 1;
    }
    NEW_MSG_ID++;
    return 0;
}

public static int displayMessages(int user, Connection sql){
	
	// SQL
	
	// Gets the messages sent directly to the user
    String ins = "SELECT * FROM messages WHERE " + user + " = r_ID AND is_user = 1";
    String ins2 = "";
    Statement s = null;
    try {
        s = sql.createStatement();
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
	
	// Gets the groups the user is in
    ins = "SELECT g_ID FROM members WHERE u_ID = " + user;
    ArrayList<Integer> ar = new ArrayList<Integer>();
    
    try {
    	s = sql.createStatement();
        ResultSet rs = s.executeQuery(ins);
        while(rs.next()){
        	ar.add(rs.getInt("g_ID")); 
        }
		
		// Finds all messages to all of the groups
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
    
	////////// SAME SETUP AS displayMessages BUT HAS A CHECK FOR DATE FEATURE ///////////////////////////
	
	
	// SQL
    String ins = "SELECT last_log FROM users WHERE user_ID = ?";
    String ins2 = "";
    ArrayList<Timestamp> ar = new ArrayList<Timestamp>();
    try {
    	s = sql.prepareStatement(ins);
        s.setInt(1, user);
        ResultSet rs = s.executeQuery();
        while(rs.next()){
        	ar.add(rs.getTimestamp("last_log"));
        }
        for (int i = 0; i < ar.size(); i++){
        	ins2 = "SELECT * FROM messages WHERE ? = r_ID AND date_sent > ?";
        	PreparedStatement s2 = sql.prepareStatement(ins2);
        	s2.setInt(1, user);
        	s2.setTimestamp(2, ar.get(i));
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

    ins = "SELECT last_log FROM users WHERE user_ID = ?";
    String ins3 = "";
    ArrayList<Timestamp> ar2 = new ArrayList<Timestamp>();

    try {
    	s = sql.prepareStatement(ins);
        s.setInt(1, user);
        ResultSet rs = s.executeQuery();
        while(rs.next()){
        	ar2.add(rs.getTimestamp("last_log"));
        }
        for (int i = 0; i < ar2.size(); i++){
        	ins2 = "SELECT g_ID FROM members WHERE u_ID = ?";
        	ArrayList<Integer> ar3 = new ArrayList<Integer>();
        	PreparedStatement s2 = sql.prepareStatement(ins2);
        	s2.setInt(1, user);
        	ResultSet rs2 = s2.executeQuery();
        	while(rs2.next()){
        		ar3.add(rs2.getInt("g_ID"));
        	}
        	for (int j = 0; j < ar3.size(); j++){
        		ins3 = "SELECT * FROM messages WHERE ? = r_ID AND date_sent > ?";
        		PreparedStatement s3 = sql.prepareStatement(ins3);
        		s3.setInt(1, ar3.get(i));
        		s3.setTimestamp(2, ar2.get(i));
        		ResultSet rs3 = s3.executeQuery();
                while(rs3.next()){
                    System.out.println("To: " + findName(user, sql));
                    System.out.println("From: " + findName(rs3.getInt("sender"), sql));
                    System.out.println("Subject: " + rs3.getString("subject"));
                    System.out.println("Body: " + rs3.getString("body") + "\n\n\n");
                }
        	}
        	
        }


    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}

public static int searchForUser(String st, Connection sql){
    
	// Splits the string into all the words in the string
	String[] all;
    all = st.split("[^\\w']");
    int flag = 0;
	
	// SQL
	
	// Loops through all the strings 
    for(int i = 0; i < all.length; i++){
        String ins = "SELECT * FROM users WHERE name LIKE ? OR email LIKE ?";
        PreparedStatement s = null;
        try {
        	s = sql.prepareStatement(ins);
            s.setString(1, all[i] + "%");
            s.setString(2, all[i] + "%");
            ResultSet rs = s.executeQuery();
            while(rs.next()){
            	flag = 1;
                System.out.println(rs.getString("name"));
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
            return 1;
        }
    }
    if (flag == 0){
    	System.out.println("No results found.");
    }
    return 0;
}
	
	public static void threeDegrees(int userA, int userB, Connection sql){
		
		// Variables
		boolean isFriend = false;
		boolean noPath = true;
		ArrayList<Integer> res = new ArrayList<Integer>();
		
		// SQL
		
		// Finds the users friends
		int[] c = displayFriends(userA, sql);
		for(int i = 0; i < c.length; i++){
			
			// if the friend is user b, flip flag and continue
			if (userB == c[i]){
				isFriend = true;
				continue;
			}
			
			// Gets friends' friends
			int[] d = displayFriends(c[i], sql);
			for (int j = 0; j < d.length; j++){
				
				// If the friend's friend is user b, add them to an arraylist
				if (userB == d[j]){
					res.add(c[i]);
					continue;
				}
				
				// Gets friends' friends' friends
				int[] e = displayFriends(d[j], sql);
				for (int k = 0; k < e.length; k++){
					
					// If the f's f's f is user b, print
					if (userB == e[k]){
						noPath = false;
						System.out.println(findName(userA, sql) + " -> " + findName(c[i], sql) + " -> " + findName(d[j], sql) + " -> " + findName(userB, sql) + "\n");
					}
				}
			}
		}
		
		// print 2 degrees
		if (!res.isEmpty()){
			noPath = false;
			for (int i = 0; i < res.size(); i++){
				System.out.println(findName(userA, sql) + " -> " + findName(res.get(i), sql) + " -> " + findName(userB, sql) + "\n");
			}
		}

		// print 1 degree
		if (isFriend){
			System.out.print(findName(userA, sql) + " -> " + findName(userB, sql) + "\n");
			noPath = false;
		}
		
		// print if no paths were found
		if (noPath){
			System.out.println("No path exists between these two users.\n");
		}
		
	}
	
	public static void topMessagers(int x, int k, Connection sql){
		
		// SQL
		
		/*
		PRINTS ALL MESSAGES
		String query = "SELECT * FROM messages";
		try {
			PreparedStatement prep = sql.prepareStatement(query);
			ResultSet rs = prep.executeQuery();
			while(rs.next()){
				System.out.println(findName(rs.getInt("sender"), sql));
				System.out.println(rs.getInt("r_ID"));
				System.out.println(rs.getInt("is_user"));
				System.out.println();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		*/
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, -x);
		java.sql.Date date = new java.sql.Date(calendar.getTime().getTime());
		String ins = "SELECT COUNT(sender) AS cnt, sender FROM messages WHERE ? " + 
				"< date_sent GROUP BY sender";
		String ins2 = "SELECT COUNT(r_ID) AS r1, r_ID FROM messages WHERE ? " +
				"< date_sent AND is_user = 0 GROUP BY r_ID";
		String ins3 = "SELECT COUNT(r_ID) AS r2, r_ID FROM messages WHERE ? " +
				"< date_sent AND is_user = 1 GROUP BY r_ID";
		String ins4 = "SELECT COUNT(u_ID) AS u1, u_ID FROM messages JOIN members ON messages.r_id = members.g_id WHERE is_user = 0 GROUP BY u_ID";
		ArrayList<Integer> count1 = new ArrayList<Integer>();
		ArrayList<Integer> sender = new ArrayList<Integer>();
		ArrayList<Integer> count2 = new ArrayList<Integer>();
		ArrayList<Integer> rec1 = new ArrayList<Integer>();
		ArrayList<Integer> count3 = new ArrayList<Integer>();
		ArrayList<Integer> rec2 = new ArrayList<Integer>();
		
		try {
			
			// Gets count of all the messages sent by user
			PreparedStatement s = sql.prepareStatement(ins);
			s.setDate(1, date);
			ResultSet rs = s.executeQuery();
			while(rs.next()){
				count1.add(rs.getInt("cnt"));
				sender.add(rs.getInt("sender"));
			}
			
			// Gets count of messages sent to all the group members
			PreparedStatement s2 = sql.prepareStatement(ins4);
			ResultSet rs2 = s2.executeQuery();
			while(rs2.next()){
				count2.add(rs2.getInt("u1"));
				rec1.add(rs2.getInt("u_ID"));
			}
			
			// Gets count of messages sent directly to the user
			PreparedStatement s3 = sql.prepareStatement(ins3);
			s3.setDate(1, date);
			ResultSet rs3 = s3.executeQuery();
			while(rs3.next()){
				count3.add(rs3.getInt("r2"));
				rec2.add(rs3.getInt("r_ID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Combines query 2 and 3 to get the total messages sent to a user either via group or directly to them
		int flag2 = 0;
		for (int j = 0; j < count2.size(); j++){
			for (int m = 0; m < count3.size(); m++){
				if (rec2.get(m) == rec1.get(j)){
					count3.set(m, (count2.get(j) + count3.get(m)));
					flag2 = 1;
				}
			}
			if (flag2 == 0){
				count3.add(count2.get(j));
				rec2.add(rec1.get(j));
			}
			
			flag2 = 0;
		}
		
		// Prints the top sender or receiver k times
		for (int i = 0; i < k; i++){
			int max = 0;
			int name = 0;
			int flag = 0;
			
			int index = 0;
			for (int j = 0; j < count1.size(); j++){
				if (count1.get(j) > max){
					max = count1.get(j);
					name = sender.get(j);
					flag = 1;
					index = j;
				}
			}
			for (int j = 0; j < count3.size(); j++){
				if (count3.get(j) > max){
					max = count3.get(j);
					name = rec2.get(j);
					flag = 3;
					index = j;
				}
			}
			if (flag == 1){
				System.out.println(findName(name, sql) + " sent " + max + " messages.");
				count1.remove(index);
				sender.remove(index);
			}
			else if (flag == 2){
				System.out.println("Group " + findGName(name, sql) + " recieved " + max + " messages.");
				count2.remove(index);
				rec1.remove(index);
			}
			else if (flag == 3){
				System.out.println(findName(name, sql) + " recieved " + max + " messages.");
				count3.remove(index);
				rec2.remove(index);
			}
			
		}
	}
	
	public static int dropUser(int id, Connection sql){
		
		// SQL
		try{
			String msg_query = "DELETE FROM messages WHERE sender="+id+" AND NOT EXISTS("
					+ "SELECT user_ID FROM users WHERE user_ID=r_ID) OR r_ID="+id+"AND NOT EXISTS("
					+ "SELECT user_ID FROM users WHERE user_ID=sender)";
			PreparedStatement prepStatement2 = sql.prepareStatement(msg_query);
			prepStatement2.executeUpdate();
			String fs_query = "DELETE FROM friendships WHERE user_a = " + id + "OR user_b = " + id;
			PreparedStatement prepStatement3 = sql.prepareStatement(fs_query);
			prepStatement3.executeUpdate();
			String user_query = "DELETE FROM users WHERE user_ID="+id;
			PreparedStatement prepStatement = sql.prepareStatement(user_query);
			prepStatement.executeUpdate();
			
			
		}
		catch(Exception e){
			System.out.println("Error: " + e);
			System.exit(1);
			return 1;
		}
		return 0;
		
	}
	
	public static void main(String[] args){
		
		// Gets the connection to database and runs the main method (run)
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