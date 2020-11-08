//2个数字求和后，保留2位整数，超出2位小数的四舍五入
function funSumByRound(num1, num2) {
    if (isNaN(num1) || isNaN(num2)) {
        return 0;
    }
    var value = Math.round(eval(num1 + "*" + num2) * 100) / 100;
    return value;
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