import { Fragment, useState } from 'react';
import styled from 'styled-components';

const Book_register = (): JSX.Element => {
	const [file, setFile] = useState();
	const [previewURL, setpreviewURL] = useState();
	const handleFileOnChange = (event) => {
		event.preventDefault();
		const reader = new FileReader();
		const file = event.target.files[0];
		reader.onloadend = () => {
			setFile(file);
			setpreviewURL(reader.result);
		};
		reader.readAsDataURL(file);
	};
	let profile_preview = null;
	if (file !== '') {
		profile_preview = (
			<img width="350px" height="400px" className="profile_preview" src={previewURL}></img>
		);
	}
	return (
		<Fragment>
			<Backg>
				<Wrap>
					<Book_image>
						{profile_preview}
						<input
							type="file"
							accept="image/jpg,impge/png,image/jpeg,image/gif"
							name="profile_img"
							onChange={handleFileOnChange}
						></input>
					</Book_image>
					<InputData>
						<label htmlFor="title">제목</label>
						<input id="title" type="text" placeholder="제목을 입력하세요"></input>
						<label htmlFor="author">저자</label>
						<input id="author" type="text" placeholder="저자를 입력해주세요"></input>
						<label htmlFor="price">가격</label>
						<input id="price" type="text" placeholder="가격을 입력해주세요"></input>
						<label htmlFor="summary">요약</label>
						<input id="summary" type="text" placeholder="줄거리 입력해주세요"></input>
					</InputData>
				</Wrap>
			</Backg>
		</Fragment>
	);
};

const Backg = styled.div`
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

const Wrap = styled.div`
	display: flex;
	flex-direction: row;
	height: 460px;
	margin: 20px;
`;
const Book_image = styled.div`
	display: flex;
	flex-direction: column;
	width: 100%;
	height: 100%;
	padding-left: 20px;
	padding-right: 20px;
	justify-content: center;
	border-right: 1px solid red;
	& > img {
		padding-bottom: 15px;
	}
`;
// const Book_info = styled.div`
// 	width: 100%;
// 	height: 100%;
// 	padding-left: 20px;
// `;
const InputData = styled.div`
	margin: 15px;
	display: flex;
	flex-direction: column;
	justify-content: center;
	& > input,
	label {
		width: 300px;
		padding: 10px;
		margin: 5px;
	}
`;
export default Book_register;
