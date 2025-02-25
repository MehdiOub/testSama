import axios, {AxiosResponse} from "axios";
import {PaginatedList} from "../dto/PaginatedList.model";
import {BaseDto} from "../dto/BaseDto.model";
import {BaseCriteria} from "../criteria/BaseCriteria.model";
import {UserDto} from "@/utils/zynerator/security/model/User.model";

const CHANGE_PASSWORD_URL = process.env.NEXT_PUBLIC_CHANGE_PASSWORD_URL as string;


axios.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token"); // Replace with your actual authorization token
        if (token) {
            config.headers['Authorization'] = token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
class AbstractService<T extends BaseDto, C extends BaseCriteria> {
    protected _url: string

    constructor(private baseUrl: string, private beanName: string) {
        this._url = baseUrl + beanName;
    }

    getList(): Promise<AxiosResponse<T[]>> {
        return axios.get(this._url);
    }

    save(item: T): Promise<AxiosResponse<T>> {
        return axios.post(this._url, item);
    }

    update(item: T): Promise<AxiosResponse<T>> {
       return axios.put(this._url, item);
    }

    delete(id: number): Promise<AxiosResponse<T>> {
        return axios.delete(this._url + 'id/' + id);
    }

    deleteList(items: T[]): Promise<AxiosResponse<string>> {
        return axios.post(this._url + 'multiple', items);
    }

    findPaginatedByCriteria(criteria: C): Promise<AxiosResponse<PaginatedList<T>>> {
        return axios.post<PaginatedList<T>>(this._url+ 'find-paginated-by-criteria', criteria);
    }

    findById(id: number): Promise<AxiosResponse<T>> {
        return axios.get<T>(this._url + 'id/' + id);
    }

    changePassword(username: string, password: string): Promise<AxiosResponse<any>> {
        let user: { username: string, password: string } = { username, password };
        console.log(this._url);

        return axios.post(this._url + "changePassword", user);
    }

    findByusername(username: string | undefined): Promise<AxiosResponse<T>> {
        console.log(this._url);
        return axios.get(this._url + "user/" + username)
    }

    findByuserId(id: number | null): Promise<AxiosResponse<T>> {
        return axios.get(this._url + "user/id" + id)
    }

    findByUserUsernameAndModelPermissionReferenceAndActionPermissionReference(username: string | undefined,entityName:string,actionReference:string): Promise<AxiosResponse<boolean>> {
        console.log(this._url);
        return axios.get(this._url + 'user/' + username + '/model/' + entityName + '/action/' + actionReference)
    }

    findByUserUserName(username: string): Promise<AxiosResponse<T[]>> {
        console.log(this._url);
        return axios.get(this._url + 'user/' + username )
    }


    handleDownload() {
        // Construct the download URL
        const objectName="compta";
        const fileName= "66.png"
        //const downloadUrl = `http://localhost:8036/api/open/minio/download/${objectName}/${fileName}`;
        const downloadUrl = `http://localhost:8036/api/open/minio/download/${objectName}`;

        // Create an anchor element and initiate the download
        const anchor = document.createElement('a');
        anchor.href = downloadUrl;
        anchor.download = objectName;
        anchor.target = '_blank'; // Open in new tab to trigger download
        document.body.appendChild(anchor);
        anchor.click();
        document.body.removeChild(anchor);
    };

    //const onUpload = (event:any) => {
    uploadAndOcr(event:any)  {
        const formData = new FormData();
        formData.append('file', event.files[0]);
        formData.append('folderPath', "compta/")
        // axios.post('http://localhost:8036/api/open/minio/upload/file/ged', formData)
        axios.post('http://localhost:8036/api/open/minio/uploadFileInSpecFolder/file/path/ged', formData)
            .then(response => {
                console.log('Upload successful hani :', response.data);
            })
            .catch(error => {
                console.error('Upload error:', error);
            });

        axios.post('http://localhost:8036/api/open/ocr/', formData)
            .then(response => {
                console.log('OCR successful:', response.data);
            })
            .catch(error => {
                console.error('OCR error:', error);
            });
    };

}

export default AbstractService;
