/**
 * 对数字进行转换
 * @param num  数字
 * @param scale 保留的小数位
 * @param type  类型
 * @returns {number} 转换之后的数字
 */
function funMath(num, scale, type) {
    if (isNaN(num)) {
        console.log("参数错误，非数字类型" + num);
        return num;
    }

    //是个整数，无需操作
    if (isInteger(num)) {
        return num;
    }

    var resultValue = 0;
    //要四舍五入
    if (type == "round") {
        //整数
        if (scale == 0) {
            resultValue = Math.round(num);
        } else if (scale == 2) {   //四舍五入，保留2位小数
            //示例：10.51*390*0.95=3893.954999, 需要=3893.96
            resultValue = Math.round(num * 1000) / 1000;
            resultValue = Math.round(resultValue * 100) / 100;
        } else {
            console.log("参数错误，未验证的小数位" + scale);
            return 0;
        }
    }
    return resultValue;
}

/**
 * 判断一个是正整数
 * @param num
 * @returns {boolean}
 */
function isInteger(num) {
    var reg = /^[0-9]+$/;

    if(reg.test(num)){
        return true;
    }
    return false;
}


/**
 * 小户型住房困难补助比例
 * @param area  面积
 * 注：>= 60平，为0
 */
function funSmallAreaRate(area) {

    //计算比例
    var proportion = 0;
    if (area < 41) {
        proportion = 0.1;
    } else if (area >= 41 && area < 42) {
        proportion = 0.095;
    } else if (area >= 42 && area < 43) {
        proportion = 0.09;
    } else if (area >= 43 && area < 44) {
        proportion = 0.085;
    } else if (area >= 44 && area < 45) {
        proportion = 0.08;
    } else if (area >= 45 && area < 46) {
        proportion = 0.075;
    } else if (area >= 46 && area < 47) {
        proportion = 0.07;
    } else if (area >= 47 && area < 48) {
        proportion = 0.065;
    } else if (area >= 48 && area < 49) {
        proportion = 0.06;
    } else if (area >= 49 && area < 50) {
        proportion = 0.055;
    } else if (area >= 50 && area < 51) {
        proportion = 0.05;
    } else if (area >= 51 && area < 52) {
        proportion = 0.045;
    } else if (area >= 52 && area < 53) {
        proportion = 0.04;
    } else if (area >= 53 && area < 54) {
        proportion = 0.035;
    } else if (area >= 54 && area < 55) {
        proportion = 0.03;
    } else if (area >= 55 && area < 56) {
        proportion = 0.025;
    } else if (area >= 56 && area < 57) {
        proportion = 0.02;
    } else if (area >= 57 && area < 58) {
        proportion = 0.015;
    } else if (area >= 58 && area < 59) {
        proportion = 0.01;
    } else if (area >= 59 && area < 60) {
        proportion = 0.005;
    } else {
        proportion = 0;
    }

    return proportion;

}