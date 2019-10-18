package cn.enigma.project.summary.test.controller;

import cn.enigma.project.common.controller.trace.annotation.HttpTrace;
import cn.enigma.project.summary.test.controller.req.TestReq;
import cn.enigma.project.summary.test.entity.TestEntity;
import cn.enigma.project.summary.test.service.CacheTest1;
import cn.enigma.project.summary.test.service.CacheTest;
import cn.enigma.project.summary.test.service.TestOneBO;
import cn.enigma.project.summary.test.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author luzh
 * Create: 2019/9/6 上午10:47
 * Modified By:
 * Description:
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController {

    private final TestService testService;
    private final CacheTest cacheTest;
    private final CacheTest1 cacheTest1;

    @Autowired
    public TestController(TestService testService, CacheTest cacheTest, CacheTest1 cacheTest1) {
        this.testService = testService;
        this.cacheTest = cacheTest;
        this.cacheTest1 = cacheTest1;
    }

    @HttpTrace
    @GetMapping("one")
    public Integer listOne() {
        return testService.listOne().size();
    }

    @HttpTrace
    @GetMapping("all")
    public Integer listAll() {
        return testService.listAll().size();
    }

    @HttpTrace
    @GetMapping("one/q")
    public List<TestOneBO> queryOne(Integer id) {
        return testService.findOne(id);
    }

    @HttpTrace
    @GetMapping("all/q")
    public TestEntity queryEntity(Integer id) {
        return testService.findEntity(id);
    }

    @HttpTrace
    @GetMapping("update")
    public TestEntity update(@RequestParam Integer id) {
        return testService.update(id);
    }

    @HttpTrace
    @GetMapping("add")
    public TestEntity add(String name) throws Exception {
        return cacheTest.add(name);
    }

    @HttpTrace
    @GetMapping("add/v1")
    public TestEntity addV1(TestReq testReq) throws Exception {
        return cacheTest1.add(testReq);
    }
}
