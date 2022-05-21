import { Product } from './services/product.service';
import { CategoryType } from "./constraints/constants";

export interface ProductDao {
  productId : number;
  name : String;
  description : String;
  price : number;
  inventory : number;
  category : CategoryType;
  keywords: string;
}

export interface IProduct extends ProductDao {
  keywordList : string[];
}
