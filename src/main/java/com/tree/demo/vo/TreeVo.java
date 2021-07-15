package com.tree.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: TreeVo
 * @Description: TODO
 * @Author: wang xiao le
 * @Date: 2021/7/15 15:34
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreeVo {
    /**
     * 节点id
     */
    private int id;
    /**
     * 节点name
     */
    private String name;
    /**
     * 父节点id
     */
    private int parent;

    /**
     * 子节点集合
     */
    private List<TreeVo> children;

    public static List<TreeVo> allTreeVoList() {

        List<TreeVo> list = new ArrayList<>();

        TreeVo heFeiTreeVo = TreeVo.builder().id(2).name("合肥市").parent(0).build();
        list.add(heFeiTreeVo);
        TreeVo bengBuTreeVo = TreeVo.builder().id(3).name("蚌埠市").parent(0).build();
        list.add(bengBuTreeVo);
        TreeVo wuHuTreeVo = TreeVo.builder().id(4).name("芜湖市").parent(0).build();
        list.add(wuHuTreeVo);
        TreeVo shuShanTreeVo = TreeVo.builder().id(5).name("蜀山区").parent(2).build();
        list.add(shuShanTreeVo);
        TreeVo yaoHaiTreeVo = TreeVo.builder().id(6).name("瑶海区").parent(2).build();
        list.add(yaoHaiTreeVo);

        TreeVo bengShanTreeVo = TreeVo.builder().id(7).name("蚌山区").parent(3).build();
        list.add(bengShanTreeVo);
        TreeVo huaiShangTreeVo = TreeVo.builder().id(8).name("淮上区").parent(3).build();
        list.add(huaiShangTreeVo);

        list.add(TreeVo.builder().id(9).name("镜湖区").parent(4).build());

        list.add(TreeVo.builder().id(10).name("鸠江区").parent(4).build());


        return list;
    }


}
