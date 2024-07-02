import { Component } from '@angular/core';

@Component({
  selector: 'app-post-car',
  templateUrl: './post-car.component.html',
  styleUrl: './post-car.component.scss'
})
export class PostCarComponent {

  isSpinning: boolean = false;
  selectedFile: File | null;
  imagePreview: string | ArrayBuffer | null;
  listOfOption: Array<{ label: string; value: string }> = [];
  listOfBrands = ["BMW","AUDI","FERRARI","TESLA","VOLVO","TOYOTA","HONDA","FORD","NISSAN","HYUNDAI","LEXUS","KIA"];
  listOfType = ["Petrol","Hybrid","Diesel","Electric","CNG"];
  listOfColor = ["Red","Blue","Black","White","Silver","Grey","Orange","Purple","Brown","Pink"];
  listOfTransmission = ["Automatic","Manual"];

  onFileSelected($event: any) {
    
  }

}
