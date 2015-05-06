import java.io.*;
import java.util.Scanner;
public class config{
	public static void main(String[] args) {
		boolean judge_input = true;
		print_help();
		menu_mutil all_menu = new menu_mutil();
		while(judge_input){
			Scanner in=new Scanner(System.in);
       			 String command=in.nextLine();
       			 switch(command){
       			 	case "init menu" : {
					String main_menu = new String();
					all_menu.read_menu_config();
					for(int i = 0;i < 6;i++){
						if(!all_menu.menu_list[i].menu.equals("null")){
							main_menu = all_menu.menu_list[i].menu;
							write_menu(all_menu.menu_list[i].menu,main_menu);
							all_menu.menu_2_html_basic(all_menu.menu_list[i].menu);
							intofile("basic_html/"+all_menu.menu_list[i].menu+".html","html_output/"+all_menu.menu_list[i].menu+".html");
							for(int j = 0;j < 6;j++){
								if(!all_menu.menu_list[i].list[j].equals("null")){
									write_menu(all_menu.menu_list[i].list[j],main_menu);
								}
							}
						}
					}
					all_menu.print_menu_mutil();
       			 	};break;
       			 	case "delete article" : {
       			 		System.out.println("please input the name of article that you want delete.");
       			 		Scanner article_delete=new Scanner(System.in);
       			 		String article_delete_name = article_delete.nextLine();
       			 		article delete_article = new article(article_delete_name);
	       			 	delete_article.delete_article();
       			 	};break;
       			 	case "add article" : {
       			 		renamefile("html_output/Home.html","html_output/index.html");
       			 		System.out.println("please input the name of article!");
       			 		Scanner article_in=new Scanner(System.in);
       			 		String article_name = article_in.nextLine();
       			 		article new_article = new article(article_name);
       			 		new_article.analysis_article();
       			 		new_article.output_article_config();
       			 		new_article.article_2_html();
       			 		menu_mutil menu_tmp = new menu_mutil();
       			 		menu_tmp.add_all_short_article();
       			 		renamefile("html_output/index.html","html_output/Home.html");
       			 		// new_article. short_article_add_html();
       			 	};break;
       			 	case "help":print_help();break;
       			 	case "quit" : judge_input = false;break;
       			 	case "text" :{ 
       			 		menu_mutil menu = new menu_mutil();
       			 		menu.read_menu("config_menu/Linux");
       			 		menu.menu_2_html_basic("Linux");
       			 	}break;
       			 	default :System.out.println("please input the true command!"); break;
       			 }
		}
			//init the navigation menu ,copy from file menu 	config_menu_builder
			//update menu_global 					menu_html_builder	
		//put in add_article_config 
			//update menu_article_temp to menu			config_menu_article_temp_builder
				//article analyze > menu >time >list
				//		  >save article_short
				//put article into menu_config 	and article	config_complete

		//put in html_init
			//init 
			 	//update all html >menu and article
		//put in add_article_next
			//update add_menu_article html 			html_builder
			//update add_article html 				html_builder
		//put in end
			//end
	}
	
	public static void write_add_article(String tag,String article){
		String temp = new String();
		try{
			mkfile(tag + "_article_temp");
			FileWriter tempWrite = new FileWriter(tag + "_article_temp",true);
			tempWrite.write(article + '\n');
			tempWrite.close();

		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}

	//create navigation menu from menu_config,basic menu copy from file menu.
	public static void write_menu(String tag,String main_menu){
		String temp = "$menu_main"+'\n';
		final String menu= "menu";	
		try{
			FileWriter tempWrite = new FileWriter("config_menu/"+tag);
			tempWrite.write(temp,0,temp.length());
			temp = '\t' + main_menu+'\n';
			tempWrite.write(temp,0,temp.length());
			tempWrite.close();
			addfile("menu","config_menu/"+tag);
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}

	public static void file_delete_string(String file_name,String delete_string,int times){//delete a String in file at meet int times times.
		try{
			int judge = 1;
			String temp = new String();
			FileWriter file_writer = new FileWriter("temp");
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				if(!temp.equals(delete_string)){
					temp = temp + '\n';
					file_writer.write(temp,0,temp.length());
				}else{
					judge++;
				}
			}
			flie_reader.close();
			file_writer.close();
			deletefile(file_name);
			renamefile(file_name,"temp");
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void addfile(String file_name,String add_file_name){//add a file in the last of another file.
		try{
			String temp = new String();
			FileWriter file_writer = new FileWriter(add_file_name,true);
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				temp = temp + '\n';
				file_writer.write(temp,0,temp.length());
			}
			flie_reader.close();
			file_writer.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void intofile(String file_name,String replace_file_name){//replace a file in the last of another file.
		try{
			String temp = new String();
			FileWriter file_writer = new FileWriter(replace_file_name);
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				temp = temp + '\n';
				file_writer.write(temp,0,temp.length());
			}
			flie_reader.close();
			file_writer.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void mkfile(String file_name){//creat a file ,if there was a file,do nothing.
		try{
			FileWriter file = new FileWriter(file_name,true);
			file.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void renamefile(String new_name,String old_name){//rename file.
		File  file= new File(old_name);
		file.renameTo(new   File(new_name));
	}
	public  static void deletefile(String file_name){//delete file.
		File file = new File(file_name);
		file.delete();
	}
	public static void print_help(){
		try{
			BufferedReader file = new BufferedReader(new FileReader("help"));
			String file_read = new String();
			while((file_read = file.readLine()) != null){
				System.out.println(file_read);
			}
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
}

class menu_single{
	String menu = new String("null");
	String[] list = new String[6];
	boolean main_menu;
	menu_single(){
		for(int i=0;i<6;i++){
  			list[i]="null";
		}
		main_menu = false;
	}
	public void print_menu_single(){
		if(main_menu){
			System.out.println("main_menu: "+menu);
		}
		if(!menu.equals("null") )
		System.out.println(menu);
		for(int i =0;i < 6&&!list[i] .equals("null");i++){
			System.out.println("\t"+list[i]);
		}
	}
}
class menu_mutil{
	int max_of_menu = 6;
	menu_single[] menu_list = new menu_single[6];
	menu_mutil(){
		for(int i=0;i<max_of_menu;i++){
  			menu_list[i]=new menu_single();
		}
	}
	public void print_menu_mutil(){
		for (int i =0;i < max_of_menu;i++ ) {
			this.menu_list[i].print_menu_single();
		}
	}
	public  void read_menu(String menu_file){//get class menu from menu config
		String config = new String();
		String menu_main = new String();
		int menu_num = -1;
		int list_num = 0;
		try{
			BufferedReader read_menu_file= new BufferedReader(new FileReader(menu_file));
			while((config = read_menu_file.readLine()) != null){
				switch(config){
					case "$menu_main":{
						config = read_menu_file.readLine();
						config = config.substring(1,config.length());
						menu_main = config;
					};break;
					case "$menu" :{
						menu_num++;
						config = read_menu_file.readLine();
						config = config.substring(1,config.length());
						if(config.equals(menu_main)){
							this.menu_list[menu_num].main_menu =true;
						}
						this.menu_list[menu_num].menu = config;
						list_num = 0;
					};break;
					case "\t$list" :{
						while((config = read_menu_file.readLine()) != null&&(!config.equals("\t$list_end"))){
							config = config.substring(2,config.length());
							this.menu_list[menu_num].list[list_num] = config;
							list_num ++;
						}
					};break;
				}
			}
			read_menu_file.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public void init_main_menu(){
		for(int i = 0;i < max_of_menu;i++){
			menu_list[i].main_menu = false;
		}
	}
	public void menu_2_html_basic(String menu_name){
		int the_max_menu = 6;
		int the_max_list = 6;
		boolean judge = false;
		String basic_html_file = "basic_html/";
		String basic_html = "html_head";
		try{
			intofile(basic_html_file+basic_html,"basic_html/"+menu_name+".html");
			this.init_main_menu();
			this.read_menu("config_menu/"+menu_name);
			FileWriter html_basic = new FileWriter("basic_html/"+menu_name+".html",true);
			for(int i = 0; i < the_max_menu&&!this.menu_list[i].menu.equals("null");i++){
				for(int j = 0; j < the_max_list&&!this.menu_list[i].list[j].equals("null"); j++){
					judge =true;
				}
				if(judge){
					html_basic.write(make_list_menu(this.menu_list[i]),0,(make_list_menu(this.menu_list[i])).length());
					judge =false;
				}else{
					html_basic.write(make_nolist_menu(this.menu_list[i]),0,(make_nolist_menu(this.menu_list[i])).length());
				}
			}
			html_basic.write(make_menu_end(),0,make_menu_end().length());
			html_basic.write(make_basic_html_end(menu_name),0,make_basic_html_end(menu_name).length());
			html_basic.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public String make_nolist_menu(menu_single menu_s){
		String href = new String();
		String active = " ";
		if(menu_s.menu.equals("Home")){
			href = "index";
		}else{
			href = menu_s.menu;
		}
		if(menu_s.main_menu){
			active = "active";
		}else{
			active = " ";
		}
		return	"<li class=\""+ active+"\"><a href=\"" +href+   ".html\"><span></span>"   + menu_s.menu+    "</a></li>";
	}
	public String make_menu_end(){
		return "<li><a href=\"login.html\"><span class=\"glyphicon glyphicon-user\"></span> 关于我</a></li></ul></div>";
	}

	
	public String make_basic_html_end(String menu_name){
		return "<div class=\"col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main\"><div class=\"row\"><ol class=\"breadcrumb\"><li><a href=\"index.html\"><span class=\"glyphicon glyphicon-home\"></span></a></li><li class=\"active\" href = \""
	+menu_name+".html\">"
	+menu_name+"</li></ol></div>";
	}
	public String make_list_menu(menu_single menu_s){
		int the_max_list = 6;
		String menu_html =new String();
		String href = new String();
		String active = " ";
		String team = menu_s.menu;
		if(menu_s.menu.equals("Home")){
			href = "index";
		}else{
			href = menu_s.menu;
		}
		if(menu_s.main_menu)
			active = "active";
		
		menu_html	= "<li class=\"parent "+active+"\"><a href=\""+href+".html\">" +menu_s.menu+"<span data-toggle=\"collapse\" href=\"#"+team+"\" class=\"icon pull-right\"><em class=\"glyphicon glyphicon-s glyphicon-plus\"></em></span> </a><ul class=\"children collapse\" id=\"" +team+"\">";
		for(int i =0; i < the_max_list; i++){
			if(!menu_s.list[i].equals("null")){
				menu_html  = menu_html +make_list(menu_s.list[i]);
			}
		}
		menu_html = menu_html+"</ul></li>";
		return menu_html;
	}
	public String make_list(String list){
		return "<li><a href=\""+list+".html\"><span></span>"+list+"</a></li>";
	}
	public void read_article_config(String menu){
		System.out.println("\tread_article_config  menu:"+menu);
		String config_file = "config_menu/"+menu;
		intofile("basic_html/"+menu+".html","html_output/"+menu+".html");
		addfile("basic_html/html_head_next","html_output/"+menu+".html");
		String temp = new String();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(config_file));
			while((temp = reader.readLine()) != null){
				switch (temp){
					case "$article":{
						while(!(temp = reader.readLine()).equals("$article_end")){
							System.out.println("article_all: "+temp);
							article article_temp = new article(temp);
							article_temp.get_article();
							article_temp.write_short_article(menu);
						}
						writer_page_end(menu);
					}
				}
			}
			reader.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public void add_all_short_article(){
		System.out.println("add_all_short_article");
		this.read_menu_config();
			for(int i = 0; i < max_of_menu; i++){
				if(!this.menu_list[i].menu.equals("null")){
					read_article_config(this.menu_list[i].menu);
				}
			}
	}
	public  void read_menu_config(){
		final String menu_config = "menu_config";
		final String menu_file = "menu";
		String config = new String();
		int menu_num = 0;
		int list_num = 0;
		try{
			BufferedReader read_config = new BufferedReader(new FileReader(menu_config));
			BufferedReader read_menu= new BufferedReader(new FileReader(menu_file));
			while((config = read_config.readLine()) != null){
				if(config.equals("$menu") ){
					if((config = read_config.readLine()) != null){
						config = config.substring(1,config.length());
						this.menu_list[menu_num].menu = config;
						menu_num++;
						list_num = 0;
					}
				}else if((config.substring(1,config.length())).equals("$list")){
					if((config = read_config.readLine()) != null){
						config = config.substring(2,config.length());
						this.menu_list[menu_num].list[list_num] = config;
						list_num ++;
					}
				}


			}
			read_config.close();
			read_menu.close();
		}
		catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void intofile(String file_name,String replace_file_name){//replace a file in the last of another file.
		try{
			String temp = new String();
			FileWriter file_writer = new FileWriter(replace_file_name);
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				temp = temp + '\n';
				file_writer.write(temp,0,temp.length());
			}
			flie_reader.close();
			file_writer.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void addfile(String file_name,String add_file_name){//add a file in the last of another file.
		try{
			String temp = new String();
			FileWriter file_writer = new FileWriter(add_file_name,true);
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				temp = temp + '\n';
				file_writer.write(temp,0,temp.length());
			}
			flie_reader.close();
			file_writer.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void writer_page_end(String menu){
		addfile("basic_html/html_end","html_output/"+menu+".html");
	}
}
class times{
	String time = new String();
	public void print_time(){
		System.out.println(time);
	}
}
class article{
	final int the_max_include = 36;
	String[] include = new String[the_max_include];
	String article_name =new String();
	String article_title = new String();
	times article_times = new times();
	article(String article_name_in){
		for(int i = 0; i < the_max_include; i++){
			include[i] = null;
		}
		article_name =article_name_in;
	}
	public void print_article(){
		System.out.println(article_title);
		this.article_times.print_time();
		for(int i = 0;i < the_max_include&&include[i] != null;i++){
			System.out.println(include[i]);
		}
	}
	public void output_article_config(){
		String temp = new String();
		for(int i =0;i < the_max_include;i++){
			try{
				if(include[i] != null){
					temp = "config_menu/"+include[i];
					file_delete_string(temp,"$article_end",1);
					FileWriter menu_include_article = new FileWriter(temp,true);
					menu_include_article.write(article_name+'\n',0,article_name.length()+1);
					temp =  "$article_end";
					menu_include_article.write(temp,0,temp.length());
					menu_include_article.close();
				}		
			}catch(IOException ex){
				System.out.println(ex.getMessage());
			}
		}
	}
	public void delete_article(){
		this.get_article();
		String delete_article_name =new String();
		delete_article_name =article_name;
		deletefile("article/"+delete_article_name+"_main");
		System.out.println("article_main delete!");
		deletefile("short_article/"+delete_article_name+"_short");
		System.out.println("short_delete");
		String temp = new String();
		for(int i =0;i < the_max_include&&include[i] != null; i++){
			temp = "config_menu/"+include[i];
			System.out.println(temp+delete_article_name);
			file_delete_string(temp,delete_article_name,1);
		}
	}
	public void analysis_article(){
		String article_main_name = article_name + "_main";
		String article_short_name = article_name + "_short";
		String temp = new String("null");
		try{
			BufferedReader article_file = new BufferedReader(new FileReader("article_config/"+article_name));
			FileWriter article_short_file = new FileWriter("short_article/"+article_short_name);
			FileWriter article_main_file = new FileWriter("article/"+article_main_name);
			while((temp = article_file.readLine()) != null){
				switch(temp){
					case "$title":{
						temp = article_file.readLine();
						article_title = temp;
					};break;
					case "$times":{
						temp = article_file.readLine();
						article_times.time= temp;
					};break;
					case "$short_article":{
						while((temp = article_file.readLine()) != null&&!temp.equals("$short_article_end")){
							article_short_file.write(temp,0,temp.length());
						}
					};break;
					case "$include":{
						int i = 0;
						while((temp = article_file.readLine()) != null&&!temp.equals("$include_end")){
							include[i] = temp;
							i++;
						}
					};break;
					case "$main_article":{
						while((temp = article_file.readLine()) != null&&!temp.equals("$main_article_end")){
							article_main_file.write(temp,0,temp.length());
						}
					};break;
				}
			}
			article_file.close();
			article_short_file.close();
			article_main_file.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public void get_article(){
		String temp = new String("null");
		try{
			BufferedReader article_file = new BufferedReader(new FileReader("article_config/"+article_name));
			while((temp = article_file.readLine()) != null){
				switch(temp){
					case "$title":{
						temp = article_file.readLine();
						article_title = temp;
					};break;
					case "$times":{
						temp = article_file.readLine();
						article_times.time= temp;
					};break;
					case "$include":{
						int i = 0;
						while((temp = article_file.readLine()) != null&&!temp.equals("$include_end")){
							include[i] = temp;
							i++;
						}
					};break;
				}
			}
			article_file.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}

	
	public void write_short_article(String include){
		try{
			
			FileWriter writer = new FileWriter("html_output/"+include+".html",true);
			String href = article_name+".html";
			String temp =new String();
			temp = 	"<a  href=\""
			+href+"\"><li class=\"left clearfix\"><span class=\"chat-img pull-left\"><img src=\"imag/"+article_name+"/"
			+article_name+".gif\" alt=\"User Avatar\"/></span><div class=\"chat-body clearfix\"><div class=\"header\"> <strong class=\"primary-font\">"
			+article_title+"</strong><small class=\"text-muted\">"
			+article_times.time+"</small></div><p>";
			writer.write(temp,0,temp.length());
			writer.close();
			addfile("short_article/"+article_name + "_short","html_output/"+include+".html");
			FileWriter writer2 = new FileWriter("html_output/"+include+".html",true);
			temp = 	"</p></div></li></a>";
			writer2.write(temp,0,temp.length());
			writer2.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public void article_2_html(){
		intofile("basic_html/"+include[0]+".html","html_output/"+article_name+".html");
		addfile("basic_html/article_head","html_output/"+article_name+".html");
		addfile("article/"+article_name+"_main","html_output/"+article_name+".html");
		addfile("basic_html/html_article_end","html_output/"+article_name+".html");
	}
	public static void file_delete_string(String file_name,String delete_string,int times){//delete a String in file at meet int times times.
		try{
			int judge = 1;
			String temp = new String();
			FileWriter file_writer = new FileWriter("temp");
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				if(!temp.equals(delete_string)){
					temp = temp + '\n';
					file_writer.write(temp,0,temp.length());
				}else{
					judge++;
				}
			}
			flie_reader.close();
			file_writer.close();
			deletefile(file_name);
			renamefile(file_name,"temp");
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void renamefile(String new_name,String old_name){//rename file.
		File  file= new File(old_name);
		file.renameTo(new   File(new_name));
	}
	public  static void deletefile(String file_name){//delete file.
		File file = new File(file_name);
		file.delete();
	}
	public static void addfile(String file_name,String add_file_name){//add a file in the last of another file.
		try{
			String temp = new String();
			FileWriter file_writer = new FileWriter(add_file_name,true);
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				temp = temp + '\n';
				file_writer.write(temp,0,temp.length());
			}
			flie_reader.close();
			file_writer.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
	public static void intofile(String file_name,String replace_file_name){//replace a file in the last of another file.
		try{
			String temp = new String();
			FileWriter file_writer = new FileWriter(replace_file_name);
			BufferedReader flie_reader = new BufferedReader(new FileReader(file_name));
			while((temp = flie_reader.readLine()) != null){
				temp = temp + '\n';
				file_writer.write(temp,0,temp.length());
			}
			flie_reader.close();
			file_writer.close();
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		}
	}
}