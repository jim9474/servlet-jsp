package control;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CommandProcess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private Map<String, Object> commandMap = new HashMap<String, Object>();

   /**
    * @see HttpServlet#HttpServlet()
    */
   public Controller() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see Servlet#init(ServletConfig)
    */
   public void init(ServletConfig config) throws ServletException {
      // web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
      String props = config.getInitParameter("config");		
      // WEB-INF/command.properties
      System.out.println("1. init String props=> " + props); // /WEB-INF/command.properties
      // 프로퍼티스 객체는 키,값 쌍으로 이루어져있음
      Properties pr = new Properties();
      // 파일인풋스트림은 파일데이터를 바이트단위로 읽음
      FileInputStream f = null;
      
      try {
    	  // 겟서블릿컨텍스트는 웹어플리케이션의 환경을 갖고옴, 겟리얼패스는 상대경로의 실제경로를 갖고옴
         String configFilePath = config.getServletContext().getRealPath(props);	// 탐캣서버의 메타데이터를 읽어옴
         System.out.println("2. init String configFilePath=> " + configFilePath); // 리얼경로
         f = new FileInputStream(configFilePath);	// 실제경로를 바이트단위로 읽음
         // Memory Up
         pr.load(f);	// 프로퍼티스 객체에 키와값으로 로드, 프로퍼티파일 =기준으로 키와 값으로 나눔
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         if (f != null)
            try {
               f.close();
            } catch (IOException ex) {} 
      }
      
      //      /list.do /content.do
      Iterator keyIter = pr.keySet().iterator();	// pr.keySet은 프로퍼티스객체의 모든 키들의 집함
      
      while(keyIter.hasNext())  {
          String command   = (String) keyIter.next();	// 키 list.do를 저장
          String className = pr.getProperty(command);	// 키의 값인 service.ListAction을 저장
           System.out.println("3. init command=> "+ command);  // /list.do
            System.out.println("4. init className=> "+ className);	// service.ListAction
            
            try {
                 //  ListAction la = new ListAction();
                  // 소멸 Class
            //   Class  commandClass    = Class.forName(className);
            //   Object commandInstance = commandClass.newInstance();
               // new Class   --> 제네릭의 요점은 클래스 유형을 모른다. <?>이게 제너릭타입
                  Class<?> commandClass = Class.forName(className);//해당 문자열을 클래스로 만든다. serVice.ListAction을 클래스객체로 반환
                  CommandProcess commandInstance = 
                        (CommandProcess)commandClass.getDeclaredConstructor().newInstance();	// 클래스로 만든거의 기본생성자를 인스턴트로 호출
                  			// LsitAction() commandClass = new ListAction()을 CommandProcess으로 타입변환해서
                  			// commandInstance에 저장, 결국 service.ListAcTion을 저장
               
               
               //          list.do    service.ListAction
               //         content.do  service.ContentAction
               commandMap.put(command, commandInstance);
               
             } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
             }  
      }
      
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      requestServletPro(request, response);
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      requestServletPro(request, response);
   }
   
   protected void requestServletPro(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      String view = null;
      CommandProcess com = null;
      String command = request.getRequestURI();
      System.out.println("1. requestServletPro command=> " + command); // /och16/list.do
      command = command.substring(request.getContextPath().length());
      System.out.println("2. requestServletPro cammand substring=> " + command); // /list.do
      
      try {
         //   service.ListAction Instance
         com = (CommandProcess) commandMap.get(command);
         System.out.println("3.requestServletPro command=> " + command); // /list.do
         System.out.println("4.requestServletPro com=> " + com); // /service.ListAction@해시값
         //   com --> service.ListAction@32a22787
         view = com.requestPro(request, response);	// ListAction.java의 리퀘스트프로를 실행
         System.out.println("5.requestServletPro view => " + view); // /listForm.jsp
      
      } catch (Exception e) {
         throw new ServletException(e);
      }
      
      RequestDispatcher dispatcher = request.getRequestDispatcher(view);
      dispatcher.forward(request, response);
   }
}