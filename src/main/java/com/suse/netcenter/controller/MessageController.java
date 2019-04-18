package com.suse.netcenter.controller;

import com.suse.netcenter.annotation.UserLoginToken;
import com.suse.netcenter.dto.Msg;
import com.suse.netcenter.entity.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Tangerg
 * @create 2019-04-04 15:13
 */
@Api("消息相关api")
@RestController
@RequestMapping("/message")
public class MessageController extends BaseController {

    /*
    condition
        0：未读
        1：已读
        2：所有
    * */
    @ApiOperation("接收所有消息")
    @UserLoginToken
    @GetMapping("/query")
    public Msg msgQuery(@RequestParam(value = "condition", defaultValue = "2") String condition,
                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                        @RequestHeader String token) {
        return messageService.queryMsg(condition, pageNum, pageSize, token);
    }

    @ApiOperation("发送消息")
    @UserLoginToken
    @PostMapping("/send")
    public Msg msgSend(@Valid @RequestBody Message message,
                       @RequestHeader String token) {
        return messageService.sendMsg(message, token);
    }
    @ApiOperation("删除消息")
    @UserLoginToken
    @PostMapping("/delete/{id}")
    public Msg msgSend(@PathVariable("id") Integer id,
                       @RequestHeader String token) {
        return messageService.deleteMsg(id, token);
    }

    @ApiOperation("标记消息为已读")
    @UserLoginToken
    @GetMapping("/read/{id}")
    public Msg msgRead(@PathVariable("id") Integer id,
                       @RequestHeader String token) {
        return messageService.readMsg(id, token);
    }
}
