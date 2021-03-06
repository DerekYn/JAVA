/*
   >>> PrintWriter <<<
   
   -- 문자열 출력시 사용되는 2byte 기반의 필터스트림(보조스트림)이다.
   -- BufferedWriter 도 동일한 2byte 기반의 필터스트림(보조스트림)이지만
           이것을 사용해서 출력해주는 write() 메소드는 자동줄 바꿈이 없다. 
           그런데  PrintWriter 을 사용하면 println() 메소드를 사용하여 자동줄바꿈이 되고, 
           자동줄바꿈을 하기 싫으면 print() 메소드를 사용하면 되므로 PrintWriter 를 더 많이 사용한다. 
           또한 PrintWriter 를 사용하면 write() 메소드도 사용가능하다.
 
   == PrintWriter 클래스의 autoFlush ==
      출력버퍼를 얼마나 자주 flush 해야 할까? 
      
   flush 란 ? 
    -- 출력버퍼에 임시로 보관되어 스트림으로 출력될 때까지 대기중인 데이터를 스트림으로 내보내는 것을 flush 라고 한다.  
             필터(보조)스트림인 BufferedWriter 클래스는 버퍼가 가득 차거나 스트림이 정상적으로 닫힐 때 flush() 메소드를 자동으로 호출한다. 
       BufferedWriter 클래스의 flush() 메소드가 호출되기 전까지는 버퍼에 임시로 보관된 데이터는 스트림으로 실제로 출력되지 않고 버퍼에 대기하고 있게 된다. 

      그런데 필터(보조)스트림으로 BufferedWriter 보다는 주로 PrintWriter를 사용하는 이유는 
    printXXX (printf, println, 등) Method 들을 사용하기 위해서이다.
      또한 PrintWriter 클래스는 생성자 중에 autoFlush 옵션이 있는 것이 있다. 
      이 옵션이 true 값으로 설정되면 print() 또는 write() 메소드의 경우엔 상관없지만, 
    println() 메소드가 호출되면 자동으로 flush() 메소드를 호출한다. 
      그러나, 실제로 이 옵션을 사용하면 지나치게 빈번하게 버퍼를 비우는 경향이 발생한다.    
*/
package io.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class PrintWriterTest {

	public static void main(String[] args) 
			throws IOException {
		
		InputStreamReader istReader = new InputStreamReader(System.in);  
		// 키보드에서 입력하는 것은 1byte 기반인데 이것을 2byte 기반으로 변경한다.
		
		BufferedReader bufReader = new BufferedReader(istReader, 1024);
		// 필터스트림(보조스트림)을 장착한다.
		
		
		OutputStreamWriter ostWriter = new OutputStreamWriter(System.out); 
		// 모니터에 출력하는 것은 1byte 기반인데 이것을 2byte 기반으로 변경한다.
		
		PrintWriter printWriter = new PrintWriter(ostWriter, true);
		// 필터스트림(보조스트림)을 장착한다.
	/*
		   new PrintWriter(ostWriter, true); 에서
		     두번째 파라미터인 값에 true 를 주면
		   true 의 의미는 개행문자(엔터)를 만날때 마다 자동으로 flush() 메소드가 작동을 한다는 말이다. 
		     그러므로 이 옵션이 true 값으로 설정되면 print() 또는 write() 메소드의 경우에 상관없지만,
		   println() 메소드가 호출되면 자동으로 flush() 메소드를 호출한다.     
	 */
		
		printWriter.println("내용을 입력하세요 => ");
				
		String str = "";
		
		while ( (str = bufReader.readLine()) != null ) {
			/*
			   readLine() 메소드를 사용하면 읽어들이는 단위가 1줄 단위로 읽어들인다. 
			     여기서 1줄의 끝은 엔터(\r\n)이므로 엔터전까지 읽어들인다.
			     중요한 것은 엔터전까지 읽어들이므로 엔터는 읽지 않는다.      
			*/
			
			printWriter.println(str);
			
		}// end of while--------------------------
		
		printWriter.close();
		ostWriter.close();
		bufReader.close();
		istReader.close();		

	}// end of main()--------------------------------

}
