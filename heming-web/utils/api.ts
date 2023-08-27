import { useUserStore } from '~/stores/user'
import ky from 'ky'

const createApi = ({ $pinia }: { $pinia: any }): any => {
    const user = useUserStore($pinia)
    const api = ky.extend({
        hooks: {
            beforeRequest: [
                request => {
                    request.headers.set('Authorization', 'Bearer ' + user.token);
                }
            ],
            beforeError: [
                error => {
                    const {response} = error;
                    console.log(response);
                    return error;
                }
            ]
        }
    })
    return api
}

export default createApi