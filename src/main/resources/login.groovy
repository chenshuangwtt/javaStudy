class  login{
	String login(String name,String password) {
	if(name.equals("admin")&&password.equals("12345")){
		return "login success";
	}else{
		return "login failed"
	}
}
