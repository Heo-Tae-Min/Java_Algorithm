import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		Stack<Character> stack = new Stack<>();
		
		// stack을 비워야 하는 시기 : 공백, '<', 루프를 벗어난 후
		for(int i=0; i<input.length(); i++) {
			
			// 태그 처리
			// '<'의 경우 이전의 stack에 담긴 값이 있다면 뒤집어주고
			// '<'를 stack에 담아 현재 문자가 태그에 포함되어 있는지 판별
			// '>'를 만나면 stack을 pop해주어 trigger 제거
			if(input.charAt(i) == '<') {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				
				stack.push('<');
				sb.append('<');
			}
			else if(!stack.isEmpty() && stack.peek() == '<') {
				if(input.charAt(i) == '>') {
					stack.pop();
					sb.append('>');
				}
				else sb.append(input.charAt(i));
			}
			
			// 태그가 아닌 경우 중 일반 문자 or 숫자라면 stack에 push
			else if(input.charAt(i) != ' ')
				stack.push(input.charAt(i));
			// 공백을 만나면 스택 비우기
			else {
				while(!stack.isEmpty()) {
					sb.append(stack.pop());
				}
				sb.append(' ');
			}
		}
		
		// 마지막으로 스택 비우기
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		bw.write(sb.toString());
		bw.flush(); bw.close(); br.close();
	}

}