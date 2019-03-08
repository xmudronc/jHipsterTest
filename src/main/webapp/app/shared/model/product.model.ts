import { IUsr } from 'app/shared/model//usr.model';

export interface IProduct {
    id?: number;
    productID?: string;
    price?: number;
    category?: string;
    imageContentType?: string;
    image?: any;
    usr?: IUsr;
}

export class Product implements IProduct {
    constructor(
        public id?: number,
        public productID?: string,
        public price?: number,
        public category?: string,
        public imageContentType?: string,
        public image?: any,
        public usr?: IUsr
    ) {}
}
