package com.tree.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.tree.demo.util.ListResult;
import com.tree.demo.vo.TreeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: GetTreeController
 * @Description: TODO
 * @Author:
 * @Date: 2021/7/15 15:29
 **/
@RestController
@RequestMapping("/tree")
@CrossOrigin
@Slf4j
public class GetTreeController {

    /**
     * @return ListResult<List < TreeVo>>
     * @Author WXL
     * @Description 获取树形结构，不论有几层树都支持
     * @Date 2021/7/15
     * @Param
     **/
    @GetMapping(value = "/getCommonWordsTree", consumes = {"application/json"})
    public ListResult<List<TreeVo>> getCommonWordsTree() {
        ListResult<List<TreeVo>> listListResult = new ListResult<>();
        try {
            
            List<TreeVo> list = TreeVo.allTreeVoList();
            System.out.println("list = " + list);
            if (!CollectionUtils.isEmpty(list)) {

                //1、将根节点放入临时List中
                List<TreeVo> tempList = list.stream().filter(vo -> vo.getParent() == 0).collect(Collectors.toList());

                //2、将所有数据放入Treemap中,遍历所有节点
                Map<Integer, TreeVo> treeVoMap = list.stream().collect(Collectors.toMap(TreeVo::getId, e -> e));
                System.out.println("treeVoMap = " + treeVoMap);
                for (Map.Entry<Integer, TreeVo> integerTreeVoEntry : treeVoMap.entrySet()) {
                    //2.1获取当前节点
                    TreeVo treeVo = integerTreeVoEntry.getValue();
                    int parentId = treeVo.getParent();
                    //2.2获取当前节点的父节点
                    TreeVo treeVoParent = treeVoMap.get(parentId);
                    //跳过根节点
                    if (ObjectUtils.isEmpty(treeVoParent)) {
                        continue;
                    }
                    //2.3将当前节点放入父节点的Children集合中
                    if (ObjectUtils.isEmpty(treeVoParent.getChildren())) {
                        //2.3.1每新增一层，都要new ArrayList<>()
                        List<TreeVo> treeVoList = new ArrayList<>();
                        treeVoList.add(treeVo);
                        treeVoParent.setChildren(treeVoList);
                    } else {
                        //2.3.2同层之间，在一个list中新增
                        treeVoParent.getChildren().add(treeVo);
                    }
                }
                //3 最后返回tempList即可
                String treeString = JSONObject.toJSONString(tempList);
                System.out.println("treeString = " + treeString);
                listListResult.setStatus(200);
                listListResult.setData(tempList);
                listListResult.setMessage("查询树形结构成功");
            }

            return listListResult;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            listListResult.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            listListResult.setMessage("查询树形结构失败");
            return listListResult;
        }
    }
}
