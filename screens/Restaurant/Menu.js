import React, { useState } from "react";
import { ScrollView, Text, View } from "react-native";
import styled from "styled-components";
import FooterBtn from "../../components/FooterBtn";
import ViewContainer from "../../components/ViewContainer";
import constants from "../../constants";
import styles from "../../styles";

const OPTION_BTN_SIZE = 25;

const MenuImage = styled.Image`
    width : ${constants.width / 2};
    height : ${constants.width / 2};
    border-radius : ${constants.width / 10};
    position : absolute;
    top : -${constants.width / 3};
`

const MenuBrief = styled.View`
    width : ${constants.width - 20};
    justify-content : center;
    align-items : center;
    border-width : 0.7;
    border-color : ${styles.lightGrayColor};
    border-radius : 10;
    margin-top : ${constants.width / 3 + 25};
    margin-bottom : 20;
    padding-top : ${constants.width / 6 + 10};
    padding-bottom : 20;
    background-color : white;
`

const MenuName = styled.Text`
    font-size : 23;
    font-weight : 600;
    margin-bottom : 5;
`

const MenuDescription = styled.Text`
    font-size : 15;
    opacity : 0.8;
`

const OptionsList = styled.View`
    width : ${constants.width - 20};
    background-color : white;
    height : 300;
    border-width : 0.7;
    border-color : ${styles.lightGrayColor};
    border-radius : 10;
    padding-top : 20;
    padding-bottom : 20;
    padding-left : 20;
    padding-right : 20;
    margin-bottom : 20;
`

const Option = styled.View`
    padding-top : 10;
    padding-bottom : 10;
    border-bottom-width : ${styles.grayBorderWidth};
    border-color : ${styles.lightGrayColor};
`

const OptionTitle = styled.Text`
    font-size : 18;
    font-weight : 600;
    margin-bottom : 15;
`

const OptionItemContainer = styled.TouchableOpacity`
    padding-left : 20;
    padding-right : 10;
    flex-direction : row;
    justify-content : space-between;
`

const OptionItemText = styled.Text`
    width : ${constants.width - 150};
`

const OptionBtnSingleContainer = styled.View`
    width : ${OPTION_BTN_SIZE};
    height : ${OPTION_BTN_SIZE};
    border-radius : ${OPTION_BTN_SIZE / 2};
    border-width : ${styles.grayBorderWidth};
    border-color : ${styles.lightGrayColor};
    justify-content : center;
    align-items : center;
`

const OptionBtnSingleDot = styled.View`
    width : ${OPTION_BTN_SIZE / 2};
    height : ${OPTION_BTN_SIZE / 2};
    borderRadius : ${OPTION_BTN_SIZE / 4};
`

const OptionBtnSingle = ({isSelected}) => (
    isSelected ? (
        <OptionBtnSingleContainer>
            <OptionBtnSingleDot style={{
                backgroundColor : styles.themeColor
            }}/>
        </OptionBtnSingleContainer>
    ) : (
        <OptionBtnSingleContainer>
            <OptionBtnSingleDot  style={{
            backgroundColor : "#c1bdbd"
        }}/>
        </OptionBtnSingleContainer>
    )
)

// const OptionBtn

const AddBtnHeader = styled.View`
    width : ${constants.width - 40};
    height : 40;
    margin-bottom : 10;
    flex-direction : row;
    justify-content : space-between;
    align-items : center;
`

const MenuCountController = styled.View`
    width : 110;
    height : 40;
    border-color : ${styles.lightGrayColor};
    border-width : 1;
    border-radius : 20;
    flex-direction : row;
`

const CountControlBtnContainer = styled.TouchableOpacity`
    justify-content : center;
    align-items : center;
    width : 40;
    height : 40;
`

const CountControlBtn = ({isMinus, onPress}) => (
    isMinus ? (
        <CountControlBtnContainer onPress={onPress} activeOpacity={0.9}>
            <Text style={{fontSize : 16}}>-</Text>
        </CountControlBtnContainer>
    ) : (
        <CountControlBtnContainer onPress={onPress} activeOpacity={0.9}>
            <Text style={{fontSize : 16}}>+</Text>
        </CountControlBtnContainer>
    )
)

const CurrentCount = styled.Text`
    width : 30;
    height : 40;
    text-align : center;
    text-align-vertical : center;
    font-size : 15;
`

const CurrentPrice = styled.Text`
    font-size : 14.5;
    opacity : 0.75;
`

export default ({navigation, route}) => {
    const {
        params : {menuId}
    } = route;
    // const [orderMenu, setOrderMenu] = useState({
    //     count : 0,
    //     price : 0,
    //     options : []
    // });
    // const menu = 
    const menu = {
        name : "로제떡볶이",
        id : 2,
        thumbnail : "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSigAD5XWPpDiv4k6yVbCLnUEFSD829OgpWng&usqp=CAU",
        description : "색다른 기분을 위한 로제 떡볶이(1~2인)",
        price : 12000,
        isAvailable : true,
        isBestMenu : false,
        options : [
            {
                category : "맵기",
                isRequired : true,
                isMultiple : false,
                items : [
                    {
                        content : "0단계",
                        price : 0
                    },
                    {
                        content : "1단계",
                        price : 1
                    }
                ]
            },
            {
                category : "추가토핑",
                isRequired : false,
                isMultiple : true,
                items : [
                    {
                        content : "베이컨 추가",
                        price : 1000
                    },
                    {
                        content : "치즈 추가",
                        price : 1500
                    }
                ]
            }
        ]
    };
    const getPricePerOne = () => {
        let price = menu.price;
        for(option in orderMenu.options){
            price += option.price;
        }
        return price;
    }
    const [orderMenu, setOrderMenu] = useState({
        id : menu.id,
        count : 1,
        price : menu.price,
        options : []
    })
    navigation.setOptions({title : menu.name});
    const decreaseCount = () => {
        setOrderMenu({
            ...orderMenu,
            count : orderMenu.count > 1 ? orderMenu.count - 1 : orderMenu.count,
            price : orderMenu.count > 1
                ? orderMenu.price - getPricePerOne()
                : orderMenu.price
        })
    }
    const increaseCount = () => {
        setOrderMenu({
            ...orderMenu,
            count : orderMenu.count + 1,
            price : orderMenu.price + getPricePerOne()
        });
    }
    return <ViewContainer>
        <ScrollView
            showsVerticalScrollIndicator={false}
            contentContainerStyle={{flexGrow : 1, alignItems : "center"}}
        >
            <MenuBrief>
                <MenuImage source={{uri : menu.thumbnail}}/>
                <MenuName>{menu.name}</MenuName>
                <MenuDescription>{menu.description}</MenuDescription>
            </MenuBrief>
            <OptionsList>
                <Option>
                    <OptionTitle>맵기선택</OptionTitle>
                    <OptionItemContainer activeOpacity={1}>
                        <OptionItemText style={{backgroundColor : "red"}}>- 1단계dfdfasd ㅇㅇㅇㅇㅇㅇㅇㅇㄹㄹㅇ</OptionItemText>
                        <OptionBtnSingle isSelected={true}/>
                    </OptionItemContainer>
                </Option>
            </OptionsList>
        </ScrollView>
        <FooterBtn text={"장바구니에 담기"} onPress={()=>1} header={(
            <AddBtnHeader>
                <MenuCountController>
                    <CountControlBtn isMinus={true} onPress={decreaseCount} />
                    <CurrentCount>{orderMenu.count}</CurrentCount>
                    <CountControlBtn isMinus={false} onPress={increaseCount} />
                </MenuCountController>
                <CurrentPrice>{orderMenu.price} 원</CurrentPrice>
            </AddBtnHeader>
        )}/>
    </ViewContainer>
}