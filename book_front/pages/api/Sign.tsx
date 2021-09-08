import axios from 'axios';

export const SignUpApi = async (
	name: string,
	pwd: string,
	myid: string,
	phonenum: string,
	email: string,
	address: string,
) => {
	try {
		// axios.post('user-service/users');
		// axios({
		// 	url: 'http://localhost:8000/user-service/users',
		// 	method: 'POST',
		// 	data: {
		// 		name: name,
		// 		pwd: pwd,
		// 		myid: myid,
		// 		phonenum: phonenum,
		// 		email: email,
		// 		address: address,
		// 	},
		// });
		axios
			.post('/user-service/users', {
				name: name,
				pwd: pwd,
				myid: myid,
				phonenum: phonenum,
				email: email,
				address: address,
			})
			.then((response) => console.log(response));
	} catch (error) {
		console.log(error);
	}
};

const getBreeds = async () => {
	try {
		return await axios.get('https://dog.ceo/api/breeds/list/all');
	} catch (error) {
		console.error(error);
	}
};
