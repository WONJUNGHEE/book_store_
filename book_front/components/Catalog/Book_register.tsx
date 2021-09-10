import { Fragment, useState } from 'react';
import axios from 'axios';
import styled from 'styled-components';

const Book_register = (): JSX.Element => {
	const [file, setFile] = useState<string>('');
	const [previewURL, setpreviewURL] = useState<string>('');
	const [summary, setSummary] = useState<string>('');
	const [productId, setInputproductId] = useState<string>('');
	const [productName, setproductName] = useState<string>('');
	const [qty, setqty] = useState<string>('');
	const [unitPrice, setunitPrice] = useState<string>('');
	const [category, setcategory] = useState<string>('');

	const handleproductId = (e: React.ChangeEvent<HTMLInputElement>) => {
		setInputproductId(e.target.value);
	};
	const handleproductName = (e: React.ChangeEvent<HTMLInputElement>) => {
		setproductName(e.target.value);
	};
	const handleqty = (e: React.ChangeEvent<HTMLInputElement>) => {
		setqty(e.target.value);
	};
	const handleunitPrice = (e: React.ChangeEvent<HTMLInputElement>) => {
		setunitPrice(e.target.value);
	};
	const handlecategory = (e: React.ChangeEvent<HTMLInputElement>) => {
		setcategory(e.target.value);
	};
	const handlesummary = (e: React.ChangeEvent<HTMLInputElement>) => {
		setSummary(e.target.value);
	};
	const handleKeyPress = (e): void => {
		if (e.key === 'Enter') {
			register();
		}
	};

	const registeraxios = async () => {
		await axios
			.post(`http://192.168.35.111:50101/catalogs`, {
				productId: productId,
				productName: productName,
				qty: qty,
				unitPrice: unitPrice,
				category: category,
				detail: summary,
				src: previewURL,
			})
			.then(() => {
				alert('등록이 완료되었습니다.');
			})
			.catch((error) => {
				console.log(error);
			});
	};
	const registerClick = () => {
		register();
	};
	const register = (): void => {
		if (
			productId === '' ||
			productName === '' ||
			qty === '' ||
			unitPrice === '' ||
			category === '' ||
			summary === ''
		) {
			alert('정보를 모두 입력해주세요.');
		} else {
			registeraxios();
			setcategory('');
			setunitPrice('');
			setqty('');
			setproductName('');
			setInputproductId('');
			setpreviewURL('');
			setFile('');
		}
	};
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
							accept="image/jpg,image/png,image/jpeg,image/gif"
							name="profile_img"
							onChange={handleFileOnChange}
						></input>
					</Book_image>
					<InputData>
						<label htmlFor="productId">제품 ID</label>
						<input
							id="productId"
							type="text"
							placeholder="제품 고유 ID"
							onChange={handleproductId}
							onKeyPress={handleKeyPress}
						></input>
						<label htmlFor="title">책 제목</label>
						<input
							id="title"
							type="text"
							placeholder="제목을 입력하세요"
							onChange={handleproductName}
							onKeyPress={handleKeyPress}
						></input>
						<label htmlFor="qty">수량</label>
						<input
							id="qty"
							type="text"
							placeholder="현재 수량을 입력해주세요"
							onChange={handleqty}
							onKeyPress={handleKeyPress}
						></input>
						<label htmlFor="price">가격</label>
						<input
							id="price"
							type="text"
							placeholder="가격을 입력해주세요"
							onChange={handleunitPrice}
							onKeyPress={handleKeyPress}
						></input>
						<label htmlFor="summary">요약</label>
						<input
							id="summary"
							type="text"
							placeholder="요약 내용을 적어주세요"
							onChange={handlesummary}
							onKeyPress={handleKeyPress}
						></input>
						<div>카테고리</div>
						<div>
							<input
								type="radio"
								name="my-input"
								id="study"
								value="study"
								onChange={handlecategory}
							/>
							<label htmlFor="edu">교육</label>
							<input type="radio" name="my-input" id="edu" value="edu" onChange={handlecategory} />
							<label htmlFor="cartoon">만화</label>
							<input
								type="radio"
								name="my-input"
								id="social"
								value="social"
								onChange={handlecategory}
							/>
							<label htmlFor="social">사회</label>
							<ReigsterButton onClick={registerClick}>등록 하기</ReigsterButton>
						</div>
					</InputData>
				</Wrap>
			</Backg>
		</Fragment>
	);
};

const Backg = styled.div`
	width: 800px;
	height: 570px;
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
	height: 510px;
	width: 100%;
	margin: 20px;
	text-align: center;
	font-size: 15px;
`;
const Book_image = styled.div`
	display: flex;
	flex-direction: column;
	width: 40%;
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
	width: 60%;
	flex-direction: column;
	align-items: center;
	& > input,
	label {
		width: 300px;
		padding: 5px;
		margin: 5px;
		text-align: center;
	}
	& > div {
		width: 300px;
		margin: 10px;
		flex-direction: row;
	}
`;

const ReigsterButton = styled.button`
	margin-top: 15px;
	width: 150px;
	padding: 6px 12px;
	color: #fff;
	background-color: cadetblue;
	border-radius: 5px;
	font-size: 13px;
	border: none;
`;
export default Book_register;
