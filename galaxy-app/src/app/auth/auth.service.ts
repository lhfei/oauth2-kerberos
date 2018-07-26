export class AuthService {
	protected rootPath: string = 'http://galaxy.jd.local/ldap/api/v1/';

	protected handleError(error: any): Promise<any> {
	  console.error('An error occurred', error); // for demo purposes only
	  return Promise.reject(error.message || error);
	}
}