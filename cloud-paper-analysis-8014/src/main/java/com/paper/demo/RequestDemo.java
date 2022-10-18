package com.paper.demo;
import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;

public class RequestDemo {
    public static void main(String[] args) {
        String access_token="24.3caeb9c11adf5749a7176181b471d4fe.2592000.1663761587.282335-27052839";
        String path = "https://aip.baidubce.com/rest/2.0/ocr/v1/doc_analysis";
        ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
        // 设置header参数
        request.addHeaderParameter("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        // 设置jsonBody参数
        String jsonBody = "url=https://baidu-ai.bj.bcebos.com/ocr/doc_analysis.png&language_type=CHN_ENG&detect_direction=false&access_token="+access_token;
        request.setJsonBody(jsonBody);
        ApiExplorerClient client = new ApiExplorerClient();
        try {
            ApiExplorerResponse response = client.sendRequest(request);
            // 返回结果格式为Json字符串
            System.out.println(response.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
