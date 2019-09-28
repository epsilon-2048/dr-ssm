var totalRecord, currentPage;
$(function(){
    to_page(1);
});

function to_page(pn) {
    $.ajax({
        url:"selectAll",
        data: "pn="+pn,
        type:"get",
        success:function(result) {
            console.log(result)
            build_emps_table(result);
            build_page_info(result);
            build_page_nav(result);
        }
    });
}

function _category(category) {
    if (category == 0)
        return "男科";
    else if (category == 1)
        return "妇科";
    else
        return "其他我不管科";
}

function build_emps_table(result) {
    $("#emps_table tbody").empty();
    var emps = result.data.list;
    console.log(emps);
        $.each(emps, function(index, item) {
            var $chcekBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
            var $empIdTd = $("<td></td>").append(item.id);
            var $empNameTd = $("<td></td>").append(item.mname);
            var $genderTd = $("<td></td>").append(_category(item.category));
            var $emailTd = $("<td></td>").append(item.price);
            var $unit = $("<td></td>").append(item.unit);
            var $deptNameTd = $("<td></td>").append(item.remark);
            var $editButton = $("<button></button>").addClass("btn btn-primary btn-sm edit_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-pencil"))
                .append("编辑");
            $editButton.attr("edit-id",item.id);
            var $deleteButton = $("<button></button>").addClass("btn btn-danger btn-sm delete_btn")
                .append($("<span></span>").addClass("glyphicon glyphicon-trash"))
                .append("删除");
            $deleteButton.attr("del-id",item.id);
            var $btnTd = $("<td></td>").append($editButton).append(" ").append($deleteButton);
            var $tr = $("<tr></tr>");
            $tr.append($chcekBoxTd)
                .append($empIdTd)
                .append($empNameTd)
                .append($genderTd)
                .append($emailTd)
                .append($unit)
                .append($deptNameTd)
                .append($btnTd)
                .appendTo("#emps_table tbody")
            $tr.attr("id",item.id);
        });
}

function build_page_nav(result) {
    $("#page_nav_area").empty();
    var $ul = $("<ul></ul>").addClass("pagination");
    var $firstPage = $("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
    var $prePage = $("<li></li>").append($("<a></a>").attr("href","#").append("&laquo;"));
    var $lastPage = $("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
    var $nextPage = $("<li></li>").append($("<a></a>").attr("href","#").append("&raquo;"));
    if (result.data.hasPreviousPage){
        $firstPage.click(function(){
            to_page(1);
        });
        $prePage.click(function() {
            to_page(result.data.pageNum-1);
        });
    } else {
        $firstPage.addClass("disabled");
        $prePage.addClass("disabled");
    }
    if (result.data.hasNextPage){
        $lastPage.click(function() {
            to_page(result.data.pages);
        });
        $nextPage.click(function() {
            to_page(result.data.pageNum+1);
        });
    } else {
        $lastPage.addClass("disabled");
        $nextPage.addClass("disabled");
    }
    $ul.append($firstPage).append($prePage);
    $.each(result.data.navigatepageNums, function(index,item) {
        var $numLi = $("<li></li>").append($("<a></a>").attr("href","#").append(item));
        $numLi.click(function() {
            to_page(item);
        });
        if (result.data.pageNum == item){
            $numLi.addClass("active");
        }
        $ul.append($numLi);
    });
    $ul.append($nextPage).append($lastPage);
    var navEle = $("<nav></nav>").append($ul);
    $("#page_nav_area").append($ul);
}

function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前"+result.data.pageNum+"页，总共"
        +result.data.pages+"页，"+result.data.total+"记录");
    totalRecord = result.data.pages+5;
    currentPage = result.data.pageNum;
}

$("#emp_add_modal_btn").click(function() {

    reset_form("#empAddModal form");
    getCategory("#empAddModal select");
    $("#empAddModal").modal({
        backdrop: "static"
    });
});

function reset_form(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-error has-success");
    $(ele).find(".help-block").text("");
}

function getCategory (ele) {
    $(ele).empty();
    $.ajax({
        url:"getCate",
        type:"get",
        success:function(result) {
            console.log(result);
            $.each(result.date, function() {
                var optionEle = $("<option></option>").append(_category(this.category)).attr("value",this.category);
                optionEle.appendTo(ele);
            });
        }
    });
}

function validate_add_form() {
    var empName = $("#empName_add_input").val();
    var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if(!regName.test(empName)){
        show_validate_msg("#empName_add_input", "error" ,"用户名非法");
        return false;
    } else {
        show_validate_msg("#empName_add_input", "success" ,"");
    }
    var email = $("#email_add_input").val();
    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    if (!regEmail.test(email)) {
        show_validate_msg("#email_add_input", "error" ,"邮箱格式错误");
        return false;
    } else {
        show_validate_msg("#email_add_input", "success" ,"");
    }
    return true;
}

function show_validate_msg(ele, status ,msg) {
    $(ele).parent().removeClass("has-error has-success");
    if ("success" == status) {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    } else if ("error" == status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

$("#empName_add_input").blur(function(){
    var empName = this.value;
    $.ajax({
        url:"checkUser",
        type:"get",
        data:"empName="+empName,
        success:function(result) {
            if (result.code==100) {
                show_validate_msg("#empName_add_input", "success" ,"用户名可用");
                $("#emp_save_btn").attr("ajax-value", "success");
            } else {
                show_validate_msg("#empName_add_input", "error" ,result.extend.va_msg);
                $("#emp_save_btn").attr("ajax-value", "fail");
            }
        }
    });
});

$("#emp_save_btn").click(function() {

    if (!validate_add_form()) {
        return false;
    }
    if($(this).attr("ajax-value")=="fail") {
        return false;
    }
    $.ajax({
        url:"emp",
        type:"post",
        data:$("#empAddModal form").serialize(),
        success:function(result) {
            if(result.code==100){
                $("#empAddModal").modal("hide");
                to_page(totalRecord);
            } else {
                if ("undefined" != typeof(result.extend.errorField.empName)){
                    show_validate_msg("#empName_add_input", "error" ,result.extend.errorField.empName);
                }
                if ("undefined" != typeof(result.extend.errorField.email)) {
                    show_validate_msg("#email_add_input", "error", result.extend.errorField.email);
                }
            }
        }
    });
});

$(document).on("click", ".edit_btn", function(){
    getCategory("#empUpdateModal select");
    getEmp($(this).attr("edit-id"));
    $("#emp_update_btn").attr("edit-id",$(this).attr("edit-id"));
    $("#empUpdateModal").modal({
        backdrop: "static"
    });
});

$(document).on("click", ".delete_btn", function() {
    //alert($(this).parents("tr").find("td:eq(1)").text());
    var empName = $(this).parents("tr").find("td:eq(2)").text();
    var empId = $(this).attr("del-id");
    if (confirm("确认删除【"+empName+"】吗？")) {
        $.ajax({
            url:"emp/"+empId,
            type:"delete",
            data:$("#empAddModal form").serialize(),
            success:function(result) {
                to_page(currentPage);
            }
        });
    }

});

function getEmp(id) {
    $("#empUpdateModal form")[0].reset();
    $("#mname").text("");
    $("#remark").text("");

    $.ajax({
        url:"selectById/"+id,
        type:"get",
        success:function(result) {
            var empData = result.data;
            $("#name").append(empData.mname);
            $("#remark").val(empData.remark);
            $("#price").val(empData.price);
            $("#unit").val(empData.unit);
            $("#empUpdateModal select").attr("value",_category(empData.category));
        }
    });
}

$("#emp_update_btn").click(function() {
    var email = $("#email_update_input").val();
    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
    if (!regEmail.test(email)) {
        show_validate_msg("#email_update_input", "error" ,"邮箱格式错误");
        return false;
    } else {
        show_validate_msg("#email_update_input", "success" ,"");
    }
    $.ajax({
        url:"emp/"+$(this).attr("edit-id"),
        type:"put",
        data:$("#empUpdateModal form").serialize(),
        success:function(result) {
            $("#empUpdateModal").modal("hide");
            to_page(currentPage);
        }
    });
});

$("#check_all").click(function() {
    $(".check_item").prop("checked", $(this).prop("checked"));
});

$(document).on("click", ".check_item", function() {
    var flag = ($(".check_item:checked").length == $(".check_item").length);
    $("#check_all").prop("checked", flag);
});
$("#emp_delete_all_btn").click(function() {
    var empNames = "";
    var del_idstr = "";
    $.each($(".check_item:checked"), function() {
        empNames += $(this).parents("tr").find("td:eq(2)").text()+"-";
        del_idstr += $(this).parents("tr").find("td:eq(1)").text()+"-";
    })
    empNames = empNames.substring(0,empNames.length-1);
    del_idstr = del_idstr.substring(0,del_idstr.length-1);
    if (confirm("确认删除【"+empNames+"】吗？")) {
        $.ajax({
            url:"emp/"+del_idstr,
            type:"delete",
            success:function(result) {
                $("#check_all").prop("checked", false);
                to_page(currentPage);
            }
        });
    }
});
