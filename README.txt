1.Project Background:
  This is an exam system (swing UI) for user to take the exam by answering the questions one by one. 
  It contains the following fucntions:
	-an time counter
	-choosing questions to form a paper 
	-grading functions.

2. Project Structure:

  1）Source code structure
      entity
         ExamInfo
         Question
         User
         DataHandler : for parsing the questions and users infomation  from the data file.
     
      service
         ServiceLogic :  include functions : login, create paper, save user answers, grade user answers...
     
      ui
         Controller : Provide the Controlling of Logic Data flow to Client by managing serviceLogic and Swing UI.
	  test
	     SetUp : main enter
 
  2）Swing layout:
  
      ui 
         MenuFrame ：for user choosing the funtions: take test , show score, or exit
         examFrame ： for user reading and answering questions.
         LoginFrame ：for user login.
         NoticeFrame ：for user reading the exam rules. 
         WelcomeFrame ： welcome window.
     