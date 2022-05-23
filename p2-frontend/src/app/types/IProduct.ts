import { CategoryType } from "./constants";

export interface IProduct {
  productId : number;
  name : String;
  description : String;
  price : number;
  inventory : number;
  discount:number;
  category : CategoryType;
  keywords: string;
  imageUrl:string;
}