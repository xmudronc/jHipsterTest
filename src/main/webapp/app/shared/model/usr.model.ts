import { IProduct } from 'app/shared/model//product.model';

export interface IUsr {
    id?: number;
    userID?: string;
    password?: string;
    usrs?: IProduct[];
}

export class Usr implements IUsr {
    constructor(public id?: number, public userID?: string, public password?: string, public usrs?: IProduct[]) {}
}
