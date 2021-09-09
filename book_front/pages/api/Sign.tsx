import axios from 'axios';

export const LoginApi = async (myid1: string, pwd1: string) => {
	await axios
		.post('http://192.168.35.111:50001/login', {
			myid: myid1,
			pwd: pwd1,
		})
		.then((res) =>
			sessionStorage.setItem(
				'login_info',
				JSON.stringify([res.headers.token, res.headers.userid, myid1]),
			),
		);
};

export const SignUpApi = async (
	name1: string,
	pwd1: string,
	myid1: string,
	phonenum1: string,
	email1: string,
	address1: string,
) => {
	await axios.post('http://192.168.35.111:50001/users', {
		name: name1,
		pwd: pwd1,
		myid: myid1,
		phonenum: phonenum1,
		email: email1,
		address: address1,
	});
};
