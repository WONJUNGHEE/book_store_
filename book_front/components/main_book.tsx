import styled from 'styled-components';
import React, { useEffect } from 'react';
import Image from 'next/image';
import ReactImg from '../styles/images/react.png';
import Javascript from '../styles/images/javascript.png';
const Main_book = (): JSX.Element => {
	let slideIndex = 0;
	const showSlides = (): void => {
		try {
			const slides = window.document.getElementsByClassName('slider');
			for (let i = 0; i < slides.length; i++) {
				slides[i].setAttribute('style', 'display:none');
			}
			slideIndex++;
			if (slideIndex > slides.length) {
				slideIndex = 1;
			}
			slides[slideIndex - 1].setAttribute('style', 'display:inline-flex');
		} catch (error) {
			return;
		}
		setTimeout(showSlides, 5000);
	};

	useEffect(() => {
		showSlides();
	});

	return (
		<div>
			<MainSlider>
				<Slider className="slider fade">
					<Image priority src={ReactImg} width="300px" height="400px" />
					<BookInfo>
						<div>제목 : 리액트를 다루는 기술 근데 잘 못함</div>
						<div>저자 : 잘생긴 원정희</div>
						<div>가격 : 100만원</div>
					</BookInfo>
				</Slider>
				<Slider className="slider fade">
					<Image src={Javascript} width="300px" height="400px" />
					<BookInfo>
						<div>제목 : 자바스크립트는 자바가 아니에요</div>
						<div>저자 : 못생긴 원정희</div>
						<div>가격 : 1000만원</div>
					</BookInfo>
				</Slider>
				{/* <Slider className="slider fade">
					<Image src={ReactImg} width="300px" height="400px" />
					<BookInfo>
						<div>제목 : 리액트를 다루는 기술</div>
						<div>저자 : 원정희222</div>
						<div>가격 : 500만원</div>
					</BookInfo>
				</Slider> */}
			</MainSlider>
		</div>
	);
};

const MainSlider = styled.section`
	width: 800px;
	height: 500px;
	margin: 20px auto;
	background-color: whitesmoke;
	border-radius: 20px;
	position: relative;
	& > div {
		position: absolute;
	}
`;

const Slider = styled.div`
	width: 800px;
	height: 500px;
	animation-name: fade;
	animation-duration: 2s;
	justify-content: center;
	align-items: center;
	flex-direction: row;
	@keyframes fade {
		from {
			opacity: 0.5;
		}
		to {
			opacity: 1;
		}
	}
`;

const BookInfo = styled.div`
	width: 300px;
	margin: 30px;
`;
export default Main_book;
