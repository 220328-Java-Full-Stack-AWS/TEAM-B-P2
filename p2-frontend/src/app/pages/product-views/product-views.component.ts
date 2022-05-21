import { CategoryType } from './../../constraints/constants';
import { IProduct} from 'src/app/IProduct';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';


@Component({
  selector: 'app-product-views',
  templateUrl: './product-views.component.html',
  styleUrls: ['./product-views.component.css']
})
export class ProductViewsComponent implements OnInit {

  constructor(private productService: ProductService,
    private route: ActivatedRoute) { }

  public productList: IProduct[] = [];

  ngOnInit(): void {
    const categoryType = this.route.snapshot.paramMap.get('category') ?? "";
    const keyword = this.route.snapshot.paramMap.get('keyword')?.toLocaleLowerCase() ?? "";

        this.productService.viewAllProducts().subscribe((data: IProduct[]) => {
      const categorizedProducts = categoryType in CategoryType
      ? data.reduce((acc, p) => {
        if (p.category === categoryType) {
          acc.push(p)
        }
        return acc;
      }, [] as IProduct[])
      :
      data;

      this.productList = categorizedProducts.filter(p => p.keywords.includes(keyword) ||
       p.description.toLocaleLowerCase().includes(keyword) ||
       p.name.toLowerCase().includes(keyword))

    });
  }

}
