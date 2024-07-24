package top.singi.demo.controller;

import cn.hutool.core.io.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${custom.file.upload.path}")
    String fileUploadPath;

    @PostMapping
    Map<String, Object> upload(@RequestBody MultipartFile file) throws IOException {
        var res = new HashMap<String, Object>();
        res.put("name", file.getName());
        res.put("original_name", file.getOriginalFilename());
        res.put("type", file.getContentType());
        res.put("size", file.getBytes().length);

        res.put("transferTo", (new ClassPathResource(fileUploadPath)).getURL().getPath() + file.getOriginalFilename());

        file.transferTo(new File(res.get("transferTo").toString()));

        return res;
    }

    @GetMapping("/{path}")
    void download(HttpServletResponse httpServletResponse, @PathVariable("path") String path) throws IOException {
        File file = new ClassPathResource(fileUploadPath + path).getFile();

        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/octet-stream");
        httpServletResponse.setContentLength((int) file.length());
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + file.getName());

        FileUtil.writeToStream(file, httpServletResponse.getOutputStream());
    }
}
