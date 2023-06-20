package com.ai.posegame.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public void testGET() {
        // 소켓을 선언한다.
        try (Socket client = new Socket()) {
            // 소켓에 접속하기 위한 접속 정보를 선언한다.
            InetSocketAddress ipep = new InetSocketAddress("127.0.0.1", 9999);
            // 소켓 접속!
            client.connect(ipep);
            // 소켓이 접속이 완료되면 inputstream과 outputstream을 받는다.
            try (OutputStream sender = client.getOutputStream(); InputStream receiver = client.getInputStream();) {
                // 메시지는 for 문을 통해 10번 메시지를 전송한다.
                for (int i = 0; i < 10; i++) {
                    // 전송할 메시지를 작성한다.
                    String msg = "java test message - " + i;
                    // string을 byte배열 형식으로 변환한다.
                    byte[] data = msg.getBytes();
                    // ByteBuffer를 통해 데이터 길이를 byte형식으로 변환한다.
                    ByteBuffer b = ByteBuffer.allocate(4);
                    // byte포멧은 little 엔디언이다.
                    b.order(ByteOrder.LITTLE_ENDIAN);
                    b.putInt(data.length);
                    // 데이터 길이 전송
                    sender.write(b.array(), 0, 4);
                    // 데이터 전송
                    sender.write(data);

                    data = new byte[4];
                    // 데이터 길이를 받는다.
                    receiver.read(data, 0, 4);
                    // ByteBuffer를 통해 little 엔디언 형식으로 데이터 길이를 구한다.
                    b = ByteBuffer.wrap(data);
                    b.order(ByteOrder.LITTLE_ENDIAN);
                    int length = b.getInt();
                    // 데이터를 받을 버퍼를 선언한다.
                    data = new byte[length];
                    // 데이터를 받는다.
                    receiver.read(data, 0, length);

                    // byte형식의 데이터를 string형식으로 변환한다.
                    msg = new String(data, "UTF-8");
                    // 콘솔에 출력한다.
                    System.out.println(msg);
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/test3")
    public void test3GET(Model model){
        String title = "pose1";
        model.addAttribute("imgUrl", "/assets/pictures/" + title + ".jpg");
        model.addAttribute("title", title);
    }

    @GetMapping("/test2")
    public void test2GET(Model model){
        String title = "pose1";
        model.addAttribute("imgUrl", "/assets/pictures/" + title + ".jpg");
        model.addAttribute("title", title);
    }

    @PostMapping("/ajax")
    @ResponseBody
    public Map<String, String> ajaxPOST(){
        log.info("ajax.................................................................");

        String title = "pose2";
        Map<String, String> map = new HashMap<String, String>();
        map.put("imgUrl", "/assets/pictures/" + title + ".jpg");
        map.put("title", title);

        return map;
    }

}