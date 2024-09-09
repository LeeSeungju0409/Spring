package com.yedam.app.upload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	@Value("${file.upload.path}") // 환경변수 및 Properties 파일의 변수 값을 Read // 실제 저장 경로 지정. 하드코딩이 아니라 외부에서 값을 불러낼거임. 스프링(롬복X). 프로젝트 실행 시 강제로 변수값 넘겨주는 경우가 있음. 코드 내부가 아닌 실행 시점에 같이 변수가 생성되는거여서 별도 작업 필요. 시스템 환경 변수 설정 필요.
	// 스프링에서 사용하는 방식 = value . 
		//메모리에 올라와있는 변수값을 하는거여서 표현이 다름. 클래스 내부 어디에도 없는 변수값을 찾아내는 방식.
		// 파일 저장하는 경로를 D:에 업로드라는 파일 생성. -> 환경변수 넘겨주기. project -=> run as -> run configration -> environment (프로젝트 실행 시 환경변수 등록함) -> 달러표현식에 넣었던 변수명 , 경로 (이름 : file.upload.path / 경로 : D:/upload/)
		// junit Aop test에서도 해야하지만 심플하게. application.properties에 집어넣을거임. (# upload path)
				//스프링에서 검색했을때 등록되어있지않는 대상이 있다는 뜻에서 Warnning이 뜰 거임.
		//이 프로젝트는 집에서도 개별설정해야 함. 깃허브에 올릴 때 안넘어감.
		
		// java -jar -Dfile.upload.path=D:/upload/ boot01.jar -- 0909 메모장 참고.
	// 실행 값에 변동될 수 있도록 설정한것.
	private String uploadPath;
	
	@GetMapping("formUpload") //페이지 열기. 
	public void formUploadPage() { //메소드. void = 경로와 내가 찾고자하는 파일이 같을 때 사용.
	//return "formUpload"; //return값과 경로가 똑같으니까 굳이 필요없음.
	// 뷰리졸버가 사용하는 프리픽스, ? 를 사용해서
	// classpath:/templates/formUpload.html 로 설정. - form태그 사용해서 제대로 잘 받아오는지 html파일 생성하기. 폼태그가 가진 경로때문에 타임리프 적용되어야 함. 경로는 액션을 기반으로 생성.
		System.out.println(uploadPath);
	}
	
	@PostMapping("uploadForm")
	public String formUploadFile
					(@RequestPart MultipartFile[] files) { //file=key file을 기반으로 . 여러개 해야돼서 @RequestPart MultipartFile file이었다가 지금처럼 바꿨음.
		for(MultipartFile file : files) { // 반복문이니까 배열 추가
					System.out.println(file.getOriginalFilename()); // getOriginalFilename 사용자가 넘겨준 file에 대한 정보를 그대로 받을 때 쓰는 방식. getName과 똑같음. 
														//getContentType은 확장자 확인할 때. 이미지가 제이슨인지 뭔지. 
														//getSize는 크기. 
														//파일이 가진 데이터를 하려면 getBytes. 
														//getInputStream은 직접적으로 그 파일이 가진 내용을. 
														//transferTo(file dest)는 파일 넘겨주는 것. 
														//직접적으로 내용을 가져와야한다면 겟인풋스트림이나 겟바이트스
		System.out.println(file.getContentType()); //파일이 가진 확장자 기반으로 확장자 확인?
		System.out.println(file.getSize()); //제한 걸리면 String.valueOf를 앞에 쓰기.
		
		String fileName = file.getOriginalFilename();
		//String saveName = uploadPath + File.separator + fileName; // File.separator = "/(경로)"
		String saveName = uploadPath + fileName; // File.separator 지웠음. 어차피 패스를 쓰는거니가. 이름은 우리가 지정해야함.
		
		System.out.println("saveName : " + saveName);
		Path savePath = Paths.get(saveName); // 패스를 기반으로 스프링이 알아들 수 있도록 (패스 : 임포트에 있는 java.nio.) // path로 변환한 후
		
		try {
			file.transferTo(savePath); // transferTo: 리퀘스트 파일 읽어들여서 지정한 경로로 mapping
		} catch (IOException e) {
			e.printStackTrace();
		} // 외부로 나가서 IOException나서 빨간줄임. try/catch로 처리. IOException이기 때문에 IllegalStateException부분 지웠음.

		}
		return "redirect:formUpload";
	}
	
	
	// 실제 사용하는 형태의 메소드. 교수님꺼 복붙함.(교재내용인데 분산돼있어서 합쳐서 제공하셨음)
	@GetMapping("upload")
	public void uploadPage() {}
	
	@PostMapping("/uploadsAjax") //Ajax로 통신하는 방식 ㄱㄱ
	@ResponseBody
	public List<String> uploadFile // 핵심적으로 처리하는 부분.
			(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){ // 서버쪽에서 이미지파일만 받겠다.
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String fileName = uploadFile.getOriginalFilename();
	        
	        System.out.println("fileName : " + fileName);
	    
	        // 심플데이터 포멧으로 하면 보통 날짜 폴더 생성, UUID 이 두가지 씀. String saveName = uploadPath + File.separator + uploadFileName;
	        //날짜 폴더 생성
	        String folderPath = makeFolder(); // 파일 관리할때 필요. 현재 날짜 기반으로 정보 들고와서 날짜정보 기반으로 폴더 생성. 우리가 관리 편하려고 쓰는 거임.
	        //UUID
	        String uuid = UUID.randomUUID().toString(); // 중복되지않는 식별값 발행. 고유한 값 발생하도록 만들어진 대상. 이렇게 식별자 사용해도 쌓이는게 많기때문에 관리가 필요.
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName)); // 경로만 넣는게 아니라 확장자 뭔지 size뭔지
	     }
	    
	    return imageList;
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath); // 오늘 날짜 기준으로 경로 밑에 폴더가 있냐는 거.
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) { // 파일 클래스의 실제 존재 여부
			uploadPathFoler.mkdirs(); // 자동으로 날짜 바뀌었을때 새로운 폴더.
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
