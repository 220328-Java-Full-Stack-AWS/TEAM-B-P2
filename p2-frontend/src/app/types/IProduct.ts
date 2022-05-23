import { CategoryType } from "./constants";

export interface IProduct {
  productId : number;
  name : string;
  description : string;
  price : number;
  inventory : number;
  discount:number;
  category : CategoryType;
  keywords: string;
  imageUrl:string;
}
