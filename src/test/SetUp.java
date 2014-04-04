package test;



import configFiles.Config;
import entity.DataHandler;
import entity.ExamInfo;
import service.ServiceLogic;
import ui.Controler;
import ui.ExamFrame;
import ui.LoginFrame;
import ui.MenuFrame;
import ui.NoticeFrame;
import ui.WelcomeFrame;

public class SetUp {


	public static void main(String[] args) {


		try {
			LoginFrame lf = new LoginFrame();
			MenuFrame mf = new MenuFrame();
			ExamFrame ef = new ExamFrame();
			ExamInfo ei = new ExamInfo();
			NoticeFrame rf = new NoticeFrame();
			WelcomeFrame wf = new WelcomeFrame();
			Config cfg = new Config("client.properties");
			DataHandler dh = new DataHandler(cfg);
			ServiceLogic sl = new ServiceLogic();
			
			
			Controler ctrl = new Controler(dh);
			ctrl.setExamFrame(ef);
			ctrl.setLoginFrame(lf);
			ctrl.setMenuFrame(mf);
			ctrl.setLogic(sl);
			ctrl.setExaminfo(ei);
			ctrl.setWelcomeFrame(wf);
			
			sl.setDatahandling(dh);
			sl.setExamInfo(ei);
			sl.setControler(ctrl);
			mf.setControler(ctrl);
			lf.setControler(ctrl);
			ef.setControler(ctrl);
			ef.setExaminfo(ei);
			ei.setLogic(sl);
			ei.setControler(ctrl);
			rf.setControler(ctrl);
			mf.setExamInfo(ei);
			mf.setRulesFrame(rf);
			ctrl.welcome();
		} catch (Exception e) {

			e.printStackTrace();
		}
		

	}

}
