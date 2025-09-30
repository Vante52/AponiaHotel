import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'totalReacciones'
})
export class TotalReaccionesPipe implements PipeTransform {

  transform(reactions: any): number {
    if (!reactions) return 0;
    
    // Si reactions es un número, devolverlo directamente
    if (typeof reactions === 'number') {
      return reactions;
    }
    
    // Si reactions es un objeto, sumar todas las reacciones
    if (typeof reactions === 'object') {
      return Object.values(reactions).reduce((sum: number, value: any) => {
        return sum + (typeof value === 'number' ? value : 0);
      }, 0);
    }
    
    return 0;
  }

}
