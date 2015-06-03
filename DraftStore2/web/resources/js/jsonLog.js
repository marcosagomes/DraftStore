function getString(s, e)
{
    if (confirm("Deseja realmente gerar o log das alterações do mês atual ?"))
    {
        s.setAttribute('download', '');
        s.setAttribute('href', e);
    } else {
        s.removeAttribute('download');
        s.removeAttribute('href');
        s.setAttribute('href', '');
    }
}